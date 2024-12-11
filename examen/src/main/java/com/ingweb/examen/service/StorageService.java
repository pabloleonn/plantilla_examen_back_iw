package com.ingweb.examen.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface StorageService {

    String uploadImage(MultipartFile file, String filename) throws Exception;

    void deleteImage(String file) throws Exception;

    public String getFilename(MultipartFile file) ;

}