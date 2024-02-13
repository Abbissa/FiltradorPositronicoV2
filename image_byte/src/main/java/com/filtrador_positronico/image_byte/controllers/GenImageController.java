package com.filtrador_positronico.image_byte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.filtrador_positronico.image_byte.services.gen_image_service.GenImageService;

import java.util.Map;

@RequestMapping("/gen_image")
@Controller
public class GenImageController {

    @Autowired
    private GenImageService genImageService;

    @GetMapping("")
    public String list(Map<String, Object> model) {

        model.put("gen_images", genImageService.findAllGenImage());

        return "list";
    }
}
