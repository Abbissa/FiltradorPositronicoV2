package com.filtrador_positronico.image_byte.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.filtrador_positronico.image_byte.dto.SourceImageDTO;
import com.filtrador_positronico.image_byte.models.SourceImage;
import com.filtrador_positronico.image_byte.services.SourceImageService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequestMapping("/sourceImage")
public class SourceImageApiController {

    @Autowired
    private SourceImageService sourceImageService;

    @GetMapping("")
    public List<SourceImage> getSourceImages() {
        return sourceImageService.getAllSourceImage();
    }

    @GetMapping("/user")
    public List<SourceImage> getSourceImagesByUser(@RequestParam Long userId) {
        return sourceImageService.getAllSourceImageByUser(userId);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public SourceImage saveSourceImage(@ModelAttribute SourceImageDTO sourceImage) {
        try {
            return sourceImageService.saveSourceImage(sourceImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public SourceImage getSourceImage(@RequestParam Long id) {
        return sourceImageService.getSourceImage(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSourceImage(@RequestParam Long id) {
        sourceImageService.deleteSourceImage(id);
    }

}
