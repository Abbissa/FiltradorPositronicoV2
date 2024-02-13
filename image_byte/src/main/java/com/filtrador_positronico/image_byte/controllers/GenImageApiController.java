package com.filtrador_positronico.image_byte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filtrador_positronico.image_byte.dto.GenImageDTO;
import com.filtrador_positronico.image_byte.models.GenImage;
import com.filtrador_positronico.image_byte.services.gen_image_service.GenImageService;

@RestController
@RequestMapping("/gen_image")
public class GenImageApiController {

    @Autowired
    private GenImageService genImageService;

    @PostMapping("")
    public ResponseEntity<GenImage> createGenImage(@RequestBody GenImageDTO genImageDTO) {

        GenImage genImage = genImageService.saveGenImage(genImageDTO);
        if (genImage == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(genImage);

    }

}
