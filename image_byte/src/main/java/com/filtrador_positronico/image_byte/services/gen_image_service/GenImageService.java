package com.filtrador_positronico.image_byte.services.gen_image_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filtrador_positronico.image_byte.dto.GenImageDTO;
import com.filtrador_positronico.image_byte.models.GenImage;
import com.filtrador_positronico.image_byte.models.ImageByte;
import com.filtrador_positronico.image_byte.models.SourceImage;
import com.filtrador_positronico.image_byte.repositories.GenImageRepository;
import com.filtrador_positronico.image_byte.services.ImageByteService;
import com.filtrador_positronico.image_byte.services.SourceImageService;

@Service
public class GenImageService {

    @Autowired
    private GenImageRepository genImageRepository;
    @Autowired
    private GaussianBlurService gaussianBlurService;
    @Autowired
    private DOGService dogService;
    @Autowired
    private ImageByteService imageByteService;
    @Autowired
    private SourceImageService sourceImageService;

    public GenImage getGenImage(Long id) {
        return genImageRepository.findById(id).get();
    }

    public GenImage saveGenImage(GenImageDTO genImageDTO) {
        GenImage genImage = new GenImage(genImageDTO);

        SourceImage sourceImage = sourceImageService.getSourceImage(genImageDTO.getSourceImageId());
        if (sourceImage == null) {
            return null;
        }
        genImage.setSourceImageId(sourceImage.getId());

        ImageByte imageByte = imageByteService.getImageByte(sourceImage.getImage());

        if (imageByte == null) {
            return null;
        }
        byte[] imageBlurred1 = gaussianBlurService.getGaussianBlur(imageByte,
                genImageDTO.getConfig().getVariance(),
                genImageDTO.getConfig().getRadius());
        if (imageBlurred1 == null) {

            return null;
        }
        byte[] imageBlurred2 = gaussianBlurService.getGaussianBlur(imageByte,
                genImageDTO.getConfig().getVariance() * genImageDTO.getConfig().getVariance_scalar(),
                genImageDTO.getConfig().getRadius());

        if (imageBlurred2 == null) {

            return null;
        }

        byte[] dog = dogService.getDOG(imageBlurred1, imageBlurred2, genImageDTO.getConfig());

        if (dog == null) {
            return null;
        }
        Long imageId = imageByteService.saveImageByte(dog).getId();
        genImage.setImage(imageId);

        return genImageRepository.save(genImage);
    }

    public void deleteGenImage(Long id) {
        genImageRepository.deleteById(id);
    }

    public GenImage updateGenImage(GenImage genImage) {
        return genImageRepository.save(genImage);
    }

    public List<GenImage> findAllGenImage() {
        return genImageRepository.findAll();
    }

}
