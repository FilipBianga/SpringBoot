package pl.bianga.zamowbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Audytowanie naszych encji (data w orders)
@EnableJpaAuditing
@SpringBootApplication
public class ZamowbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZamowbookApplication.class, args);
    }

}
