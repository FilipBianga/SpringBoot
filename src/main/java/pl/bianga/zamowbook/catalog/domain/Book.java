package pl.bianga.zamowbook.catalog.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pl.bianga.zamowbook.jpa.BaseEntity;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@ToString(exclude = "authors")
@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Book extends BaseEntity {

    private String title;
    private Integer year;
    private BigDecimal price;
    private Long coverId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable
    @JsonIgnoreProperties("books")
    private Set<Author> authors = new HashSet<>();

    public Book(String title, Integer year, BigDecimal price) {
        this.title = title;
        this.year = year;
        this.price = price;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this);
    }

    public void removeAuthors() {
        Book self = this;
        authors.forEach(author -> author.getBooks().remove(self));
        authors.clear();
    }
}