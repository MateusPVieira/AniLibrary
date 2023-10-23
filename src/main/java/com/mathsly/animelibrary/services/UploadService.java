package com.mathsly.animelibrary.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {
    @Value("${upload.directory}")
    private String uploadDirectory;

    public String uploadCover(MultipartFile file, String title) throws IOException {
        createUploadDirectoryIfNotExists();

        String fileName = generateFileName(file, title);

        Path filePath = Paths.get(uploadDirectory, fileName);
        Files.write(filePath, file.getBytes());

        return fileName;
    }

    private void createUploadDirectoryIfNotExists() {
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private String generateFileName(MultipartFile file, String title) {
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String sanitizedTitle = title.replaceAll(" ", "_").toLowerCase();
        return sanitizedTitle + "Cover." + extension;
    }
}
