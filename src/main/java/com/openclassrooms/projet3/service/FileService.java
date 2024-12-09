package com.openclassrooms.projet3.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final Path root = Paths.get("src/main/resources/static/images");

    public String saveFile(MultipartFile file) throws Exception {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), root.resolve(filename));
            return "/images/" + filename;
        } catch (Exception e) {
            throw new Exception("Could not store the file. Error: " + e.getMessage());
        }
    }
}
