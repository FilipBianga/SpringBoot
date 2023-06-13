package pl.bianga.zamowbook.uploads.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.bianga.zamowbook.uploads.application.ports.UploadUseCase;
import pl.bianga.zamowbook.uploads.db.UploadJpaRepository;
import pl.bianga.zamowbook.uploads.domain.Upload;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
class UploadService implements UploadUseCase {
    private final UploadJpaRepository repository;

    @Override
    public Upload save(SaveUploadCommand command) {
        Upload upload = new Upload(
                command.getFilename(),
                command.getContentType(),
                command.getFile()
                );
        repository.save(upload);
        log.info("Upload saved: " + upload.getFilename() + "and id: " + upload.getId());
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
