package pl.bianga.zamowbook.catalog.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer year;


    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
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
