package org.zin.com.phoneshopapi.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    private final String UPLOAD_DIR = "uploads/";

    public String save(MultipartFile file, String folder) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path path = Paths.get(UPLOAD_DIR, folder, fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return folder + "/" + fileName;
    }
}
