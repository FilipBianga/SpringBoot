package pl.bianga.zamowbook.uploads.application.ports;

import lombok.AllArgsConstructor;
import lombok.Value;
import pl.bianga.zamowbook.uploads.domain.Upload;

public interface UploadUseCase {
    Upload save(SaveUploadCommand command);

    @Value
    @AllArgsConstructor
    class SaveUploadCommand {
        String filename;
        byte[] file;
        String contentType;

    }
}
