package com.openclassrooms.projet3.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Image Management", description = "APIs for handling image resources")
public class ImageController {
    
    private final Path imageDirectory = Paths.get("uploads/images");

    @Operation(summary = "Get image by filename", description = "Retrieves an image file from the server")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Image found and returned"),
        @ApiResponse(responseCode = "404", description = "Image not found")
    })
    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveImage(
        @Parameter(description = "Name of the image file", example = "house.jpg") 
        @PathVariable String filename) {
        try {
            Path file = imageDirectory.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
            }
            return ResponseEntity.notFound().build();
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
}