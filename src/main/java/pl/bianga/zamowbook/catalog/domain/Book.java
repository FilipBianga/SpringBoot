package pl.bianga.zamowbook.catalog.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class Book {
    private final Long id;
    private final String title;
    private final String author;
    private final Integer year;

//    public Book(Long id, String title, String author, Integer year) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.year = year;
//    }
//
//    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", author='" + author + '\'' +
//                ", year=" + year +
//                '}';
//    }
    //Skomentowane wszystko wyżej ponieważ korzystamy z Lomboka który za nas będzie to tworzył wystarczy dodać beany nad klasa
}
