package com.filtrador_positronico.image_byte.dto;

import lombok.Data;

@Data

public class ConfigDTO {

    private Long id;

    // Gaussian Blur Config
    private Double variance;
    private Double variance_scalar;
    private Integer radius;

    // DOG Config
    private double threshold;
    private double scalar;
    private double phi;

}
