package pl.bianga.zamowbook.uploads.application;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.bianga.zamowbook.uploads.application.ports.UploadUseCase;
import pl.bianga.zamowbook.uploads.db.UploadJpaRepository;
import pl.bianga.zamowbook.uploads.domain.Upload;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class UploadService implements UploadUseCase {
    private final UploadJpaRepository repository;

    @Override
    public Upload save(SaveUploadCommand command) {
        Upload upload = new Upload(
                command.getFilename(),
                command.getContentType(),
                command.getFile()
                );
        repository.save(upload);
        System.out.println("Upload saved: " + upload.getFilename() + "and id: " + upload.getId());
        return upload;
    }

    @Override
    public Optional<Upload> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
