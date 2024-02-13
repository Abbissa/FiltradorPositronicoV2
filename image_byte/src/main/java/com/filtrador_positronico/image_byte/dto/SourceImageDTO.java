package com.filtrador_positronico.image_byte.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SourceImageDTO {

    private Long id;
    private MultipartFile imageByte;
    private Long userId;
    private String name;
    private String description;
}
