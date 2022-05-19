package pl.bianga.zamowbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.bianga.zamowbook.order.application.OrdersProperties;

//Audytowanie naszych encji (data w orders)
@EnableJpaAuditing
@EnableScheduling
@EnableConfigurationProperties(OrdersProperties.class)
@SpringBootApplication
public class ZamowbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZamowbookApplication.class, args);
    }

}
