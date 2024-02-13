package com.filtrador_positronico.image_byte.models;

import com.filtrador_positronico.image_byte.dto.GenImageDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class GenImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long image;
    private Long userId;
    private String name;
    private String description;
    private Long sourceImageId;
    private Long congifId;

    public GenImage(GenImageDTO genImageDTO) {
        this.id = genImageDTO.getId();
        this.userId = genImageDTO.getUserId();
        this.name = genImageDTO.getName();
        this.description = genImageDTO.getDescription();
        this.sourceImageId = genImageDTO.getSourceImageId();
        this.congifId = genImageDTO.getConfig().getId();
    }

}
