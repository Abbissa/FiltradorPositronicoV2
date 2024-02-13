package com.filtrador_positronico.image_byte.services.gen_image_service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filtrador_positronico.image_byte.models.ImageByte;
import com.filtrador_positronico.image_byte.services.ImageByteService;
import com.filtrador_positronico.image_byte.services.UtilsService;
import com.filtrador_positronico.image_byte.services.gen_image_service.threads.GenGaussianBlur;

@Service
public class GaussianBlurService {

    @Autowired
    private ImageByteService imageByteService;

    @Autowired
    private UtilsService utilsService;

    public byte[] getGaussianBlur(ImageByte imageByte, double variance, int radius) {
        try {
            return getGaussianBlur(imageByte.getImageByte(), variance, radius);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getGaussianBlur(byte[] image, double variance, int radius) throws IOException {

        GenGaussianBlur genGB = new GenGaussianBlur(utilsService.toBufferedImage(image), variance, radius);
        // Thread th = new Thread(genGB);

        // Buscar imagen en base de datos
        boolean b1 = true;
        if (b1)
            genGB.run();

        return utilsService.toByteArray(genGB.getImg(), "jpg");
    }

}
