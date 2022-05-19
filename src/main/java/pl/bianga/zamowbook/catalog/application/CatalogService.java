package pl.bianga.zamowbook.catalog.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase;
import pl.bianga.zamowbook.catalog.db.AuthorJpaRepository;
import pl.bianga.zamowbook.catalog.db.BookJpaRepository;
import pl.bianga.zamowbook.catalog.domain.Author;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.uploads.application.ports.UploadUseCase;
import pl.bianga.zamowbook.uploads.domain.Upload;

import java.util.*;
import java.util.stream.Collectors;

import static pl.bianga.zamowbook.uploads.application.ports.UploadUseCase.*;

@Service
@AllArgsConstructor
class CatalogService implements CatalogUseCase {

    private final BookJpaRepository repository;
    private final AuthorJpaRepository authorRepository;
    private final UploadUseCase upload;

    @Override
    public List<Book> findAll() {
        return repository.findAllEager();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Book> findOneByTitle(String title) {
        return repository.findDistinctFirstByTitleIgnoreCase(title);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return repository.
                findByTitleStartsWithIgnoreCase(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return repository
                .findByAuthors_firstNameContainsIgnoreCaseOrAuthors_lastNameContainsIgnoreCase(author, author);
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
//                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Book  addBook(CreateBookCommand command) {
        Book book = toBook(command);
        return repository.save(book);
    }

    private Book toBook(CreateBookCommand command) {
        Book book = new Book(command.getTitle(), command.getYear(), command.getPrice(), command.getAvailable());
        Set<Author> authors = fetchAuthorsById(command.getAuthors());
        updateBooks(book, authors);
        return book;
    }

    private void updateBooks(Book book, Set<Author> authors) {
        book.removeAuthors();
        authors.forEach(book::addAuthor);
    }

    private Set<Author> fetchAuthorsById(Set<Long> authors) {
        return authors
                .stream()
                .map(authorId -> authorRepository
                        .findById(authorId)
                        .orElseThrow(() -> new IllegalArgumentException("Unable to find author with id: " + authorId))
                )
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public UpdateBookResponse updateBook(UpdateBookCommand command) {
        return repository
                .findById(command.getId())
                .map(book -> {
                    updateFields(command, book);
                    return UpdateBookResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateBookResponse(false, Collections.singletonList("Book not found with id: " + command.getId())));
    }

    private Book updateFields (UpdateBookCommand command, Book book) {

            if (command.getTitle() != null){
                book.setTitle(command.getTitle());
            }
            if (command.getAuthors() != null && command.getAuthors().isEmpty()){
                updateBooks(book, fetchAuthorsById(command.getAuthors()));
            }
            if (command.getYear() != null){
                book.setYear(command.getYear());
            }
            if (command.getPrice() != null){
                book.setPrice(command.getPrice());
            }
            return book;

    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateBookCover(UpdateBookCoverCommand command) {
        repository.findById(command.getId())
                .ifPresent(book -> {
                    Upload saveUpload = upload.save(new SaveUploadCommand(command.getFilename(), command.getFile(), command.getContentType()));
                    book.setCoverId(saveUpload.getId());
                    repository.save(book);
                });
    }

    @Override
    public void removeBookCover(Long id) {
        repository.findById(id)
                .ifPresent(book -> {
                    if(book.getCoverId() != null) {
                        upload.removeById(book.getCoverId());
                        book.setCoverId(null);
                        repository.save(book);
                    }
                });
    }
}