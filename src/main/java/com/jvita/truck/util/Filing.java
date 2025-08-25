package com.jvita.truck.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class Filing {

    @Value("${Jvita.upload-dir=/home/zac/PROJECTS/Jvita_contract/src/main/uploads}")
    private String uploadDIR;

    public String uploadImg(MultipartFile imgFile) throws IOException {
        
        if (imgFile.isEmpty() || imgFile == null) {
            return null;
        }

        String filename = UUID.randomUUID().toString() + "_" + imgFile.getOriginalFilename(); //build filename UUID + baseFileName

        String PATH_to_Dir = uploadDIR;
        Path uploadPath = Paths.get(PATH_to_Dir); //initialize upload directory PATH

        if (!Files.exists(uploadPath)) { //check if the dir does not exist
            Files.createDirectories(uploadPath); // creates dir
        }

        
        Path filePath = uploadPath.resolve(filename);
        Files.copy(imgFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }

    public void DeleteImg(String filename) throws IOException{
        if (filename == null) return;
        Path filePath = Paths.get(uploadDIR).resolve(filename);
        Files.deleteIfExists(filePath);  
     }
}

