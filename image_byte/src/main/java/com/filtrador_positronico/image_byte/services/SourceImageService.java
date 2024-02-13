package com.filtrador_positronico.image_byte.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filtrador_positronico.image_byte.dto.SourceImageDTO;
import com.filtrador_positronico.image_byte.models.SourceImage;
import com.filtrador_positronico.image_byte.repositories.SourceImageRepository;

@Service
public class SourceImageService {

    @Autowired
    private SourceImageRepository sourceImageRepository;
    @Autowired
    private ImageByteService imageByteService;

    public SourceImage getSourceImage(Long id) {
        return sourceImageRepository.findById(id).get();
    }

    public SourceImage saveSourceImage(SourceImageDTO sourceImageDTO) throws IOException {
        SourceImage sourceImage = new SourceImage(sourceImageDTO);

        sourceImage.setImage(imageByteService.saveImageByte(sourceImageDTO.getImageByte().getBytes()).getId());

        return sourceImageRepository.save(sourceImage);
    }

    public void deleteSourceImage(Long id) {

        imageByteService.deleteImageByte(sourceImageRepository.findById(id).get().getImage());
        sourceImageRepository.deleteById(id);
    }

    public SourceImage updateSourceImage(SourceImage sourceImage) {

        return sourceImageRepository.save(sourceImage);
    }

    public List<SourceImage> getAllSourceImage() {
        return sourceImageRepository.findAll();
    }

    public List<SourceImage> getAllSourceImageByUser(Long userId) {
        return sourceImageRepository.findByUserId(userId);
    }

}
