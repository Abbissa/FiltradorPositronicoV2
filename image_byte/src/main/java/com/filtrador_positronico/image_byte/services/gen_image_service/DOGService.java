package com.filtrador_positronico.image_byte.services.gen_image_service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filtrador_positronico.image_byte.dto.ConfigDTO;
import com.filtrador_positronico.image_byte.services.UtilsService;

@Service
public class DOGService {

    @Autowired
    private UtilsService util;

    public byte[] getDOG(byte[] imageBlurred1, byte[] imageBlurred2, ConfigDTO configDTO) {

        try {
            BufferedImage img1 = util.toBufferedImage(imageBlurred1);

            BufferedImage img2 = util.toBufferedImage(imageBlurred2);

            BufferedImage res = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < res.getHeight(); i++) {
                for (int j = 0; j < res.getWidth(); j++) {

                    int blue = (int) Math
                            .abs((1 + configDTO.getScalar()) * (img1.getRGB(j, i) & 0xff)
                                    - configDTO.getScalar() * (img2.getRGB(j, i) & 0xff));
                    int green = (int) Math
                            .abs((1 + configDTO.getScalar()) * ((img1.getRGB(j, i) & 0xff00) >> 8)
                                    - configDTO.getScalar() * ((img2.getRGB(j, i) & 0xff00) >> 8));
                    int red = (int) Math
                            .abs((1 + configDTO.getScalar()) * ((img1.getRGB(j, i) & 0xff0000) >> 16)
                                    - configDTO.getScalar() * ((img2.getRGB(j, i) & 0xff0000) >> 16));

                    double col = red + blue + green;

                    col /= 3;
                    int color = 255;

                    if (col < configDTO.getThreshold()) {
                        color = (int) (127.5 * (1 + Math.tanh(configDTO.getPhi() * (col - configDTO.getThreshold()))));

                    }
                    res.setRGB(j, i, new Color(color, color, color).getRGB());

                }
            }
            return util.toByteArray(res, "jpg");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
