package com.jvita.truck.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class Util {

    public String uploadImg(MultipartFile imgFile) throws IOException {
        String filename = UUID.randomUUID().toString() + "_" + imgFile.getOriginalFilename();

        String uploadDir = "src/main/resources/cmsUploads/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(filename);
        Files.write(filePath, imgFile.getBytes());

        return filename;
    }
}

