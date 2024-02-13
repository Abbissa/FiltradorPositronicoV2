package com.filtrador_positronico.image_byte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filtrador_positronico.image_byte.models.ImageByte;
import com.filtrador_positronico.image_byte.repositories.ImageByteRepository;

@Service
public class ImageByteService {

    @Autowired
    private ImageByteRepository imageByteRepository;

    public ImageByte saveImageByte(ImageByte imageByte) {
        return imageByteRepository.save(imageByte);
    }

    public ImageByte getImageByte(Long id) {
        try {
            return imageByteRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteImageByte(Long id) {
        imageByteRepository.deleteById(id);
    }

    public ImageByte updateImageByte(ImageByte imageByte) {

        return imageByteRepository.save(imageByte);
    }

    public List<ImageByte> getAllImageByte() {
        return imageByteRepository.findAll();
    }

    public ImageByte saveImageByte(byte[] image) {
        ImageByte imageByte = new ImageByte();
        imageByte.setImageByte(image);
        return imageByteRepository.save(imageByte);
    }

}
