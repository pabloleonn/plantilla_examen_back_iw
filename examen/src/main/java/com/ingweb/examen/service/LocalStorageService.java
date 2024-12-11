package com.ingweb.examen.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class LocalStorageService implements StorageService {

    private final Path uploadDir;
    private final S3Service s3Service;

    public LocalStorageService(@Value("${app.upload.dir}") String uploadDirPath, S3Service s3Service) throws IOException {
        this.uploadDir = Paths.get(uploadDirPath).toAbsolutePath().normalize();
        Files.createDirectories(this.uploadDir);
        this.s3Service = s3Service;
    }

    @Override
    public String uploadImage(MultipartFile file, String filename) throws Exception {
        if (!file.isEmpty() && isImage(file.getContentType())){
            // Copiar el archivo al directorio de subida
            //Path targetLocation = this.uploadDir.resolve(filename);
            //Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return s3Service.uploadToS3(file, "", getFileExtension(file));
        }

       return null;
    }

    @Override
    public void deleteImage(String file) throws Exception {
        String imageUrl = "/images/" + file;
        String filename = Paths.get(imageUrl).getFileName().toString();
        Path filePath = this.uploadDir.resolve(filename).normalize();
        Files.deleteIfExists(filePath);
    }

    private boolean isImage(String contentType) {
        return contentType.equals("image/jpeg") ||
                contentType.equals("image/png") ||
                contentType.equals("image/jpg") ||
                contentType.equals("image/gif") ||
                contentType.equals("image/bmp") ||
                contentType.equals("image/webp");
    }


    public String getFilename(MultipartFile file){

        if (file == null || file.isEmpty()) {
           return null;
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            return null;
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();

        return UUID.randomUUID() + "." + extension;
    }

    public String getFileExtension(MultipartFile file){

        if (file == null || file.isEmpty()) {
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            return null;
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();

        return extension;
    }


}

