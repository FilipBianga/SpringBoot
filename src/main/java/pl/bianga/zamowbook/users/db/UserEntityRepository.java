package pl.bianga.zamowbook.users.db;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.bianga.zamowbook.users.domain.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameIgnoreCase(String  username);
}
