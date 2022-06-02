package pl.bianga.zamowbook;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Audytowanie naszych encji (data w orders)
@EnableJpaAuditing
@Configuration
public class JpaConfiguration {
}
