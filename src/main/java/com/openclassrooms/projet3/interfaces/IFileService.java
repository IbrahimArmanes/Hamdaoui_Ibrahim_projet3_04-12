package com.openclassrooms.projet3.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String saveFile(MultipartFile file) throws Exception;
}
