package pl.bianga.zamowbook.user.db;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.bianga.zamowbook.user.domain.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameIgnoreCase(String  username);
}
