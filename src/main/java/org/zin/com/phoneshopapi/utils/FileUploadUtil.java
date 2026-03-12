package org.zin.com.phoneshopapi.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Utility class for handling file uploads.
 */
@Component
public class FileUploadUtil {
    private final String UPLOAD_DIR = "uploads/";

    /**
     * Save the uploaded file to the specified folder and return the file path.
     * 
     * @param file   the uploaded file
     * @param folder the target folder to save the file
     * @return the relative file path where the file is saved
     * @throws IOException if an I/O error occurs during file saving
     */

    public String save(MultipartFile file, String folder) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path path = Paths.get(UPLOAD_DIR, folder, fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return folder + "/" + fileName;
    }
}
