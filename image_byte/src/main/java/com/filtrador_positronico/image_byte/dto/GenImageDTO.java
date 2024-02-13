package com.filtrador_positronico.image_byte.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
public class GenImageDTO {

    private Long id;
    private Long userId;
    private Long sourceImageId;
    private String name;
    private String description;
    @JsonProperty("config")
    private ConfigDTO config;

}
