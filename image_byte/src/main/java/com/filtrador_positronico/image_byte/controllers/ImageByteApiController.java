package com.filtrador_positronico.image_byte.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filtrador_positronico.image_byte.models.ImageByte;
import com.filtrador_positronico.image_byte.services.ImageByteService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/imageByte")

public class ImageByteApiController {

    @Autowired
    private ImageByteService imageByteService;

    @GetMapping("")
    public ResponseEntity<List<ImageByte>> getMethodName() {
        return ResponseEntity.ok().body(imageByteService.getAllImageByte());
    }

    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageByte> saveImageByte(@ModelAttribute MultipartFile file) {
        try {
            ImageByte imageByte = imageByteService.saveImageByte(file.getBytes());
            return ResponseEntity.ok().body(imageByte);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageByte> getImageByte(@PathVariable Long id) {
        return ResponseEntity.ok().body(imageByteService.getImageByte(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ImageByte> deleteImageByte(@PathVariable Long id) {
        imageByteService.deleteImageByte(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<byte[]> getImageByteImg(@PathVariable Long id) {
        ImageByte imageByte = imageByteService.getImageByte(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageByte.getImageByte());
    }
}
