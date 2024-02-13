package com.filtrador_positronico.image_byte.models;

import com.filtrador_positronico.image_byte.dto.SourceImageDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SourceImage {

    public SourceImage(SourceImageDTO sourceImageDTO) {
        this.id = sourceImageDTO.getId();
        this.userId = sourceImageDTO.getUserId();
        this.name = sourceImageDTO.getName();
        this.description = sourceImageDTO.getDescription();

    }

    public SourceImage() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long image;
    private Long userId;
    private String name;
    private String description;

}
