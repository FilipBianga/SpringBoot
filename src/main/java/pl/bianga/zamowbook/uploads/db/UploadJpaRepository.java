package pl.bianga.zamowbook.uploads.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bianga.zamowbook.uploads.domain.Upload;

public interface UploadJpaRepository extends JpaRepository<Upload, Long> {
}
