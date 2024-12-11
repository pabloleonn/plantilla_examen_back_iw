package com.ingweb.examen.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class S3Service {

    private static final String SLASH = "/";
    private static final String TEMP_LOCAL_PATH = "projects/";
    public static final String ISSUE = "/issue_";

    @Value("#{'${image-extensions-allowed}'.split(',')}")
    List<String> imageExtensionsAllowedList;

    @Value("${aws.access-key}")
    String accessKey;

    @Value("${aws.secret-key}")
    String secretKey;

    @Value("${aws.bucket-name}")
    String bucketName;

    @Value("${aws.bucket-url}")
    String bucketUrl;


    private AmazonS3 getClient(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_1)
                .build();
    }


    public String uploadToS3(MultipartFile multipartFile, String path, String fileExtension) throws IOException {
        var uuid = UUID.randomUUID();
        var file = multipartFileToFile(multipartFile, uuid.toString(), fileExtension);

        String fullPath = file.getName();
        AmazonS3 s3client = getClient();

        try {
            s3client.putObject(bucketName, fullPath, file);
        } catch (AmazonServiceException exception){

            return null;
        }

        return fullPath;
    }

    private File multipartFileToFile(MultipartFile multipartFile, String uuid, String fileExtension)
            throws IOException {
        String tempFullPath = TEMP_LOCAL_PATH + uuid + "." + fileExtension;

        Path fileLocation = Paths.get(TEMP_LOCAL_PATH).toAbsolutePath().normalize();
        Files.createDirectories(fileLocation);

        var initialStream = multipartFile.getInputStream();
        var buffer = new byte[initialStream.available()];
        initialStream.read(buffer);
        var targetFile = new File(tempFullPath);
        try (OutputStream outStream = new FileOutputStream(targetFile)) {
            outStream.write(buffer);
        }
        return targetFile;
    }

}
