package com.filtrador_positronico.image_byte.services.gen_image_service.threads;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GaussianBlurThread implements Runnable {

    BufferedImage bf;
    BufferedImage res;
    int radius;
    int index;
    int iterations;

    private double[][] weights;

    public GaussianBlurThread(BufferedImage bf, BufferedImage res, int radius, int index,
            int iterations,
            double[][] weights) {

        this.bf = bf;
        this.res = res;
        this.radius = radius;
        this.index = index;
        this.iterations = iterations;
        this.weights = weights;
    }

    public void run() {
        int it = 0;
        while (it <= iterations) {
            double[][] reds = new double[radius][radius];
            double[][] greens = new double[radius][radius];
            double[][] blues = new double[radius][radius];
            int i = index - index / bf.getWidth() * bf.getWidth();
            int j = index / bf.getWidth();

            for (int j2 = 0; j2 < weights.length; j2++) {
                for (int k = 0; k < weights[j2].length; k++) {

                    int x = i + j2 - (weights.length / 2);
                    int y = j + k - (weights.length / 2);

                    double weight = weights[j2][k];

                    if (x < 0) {
                        x = Math.abs(x);
                    }
                    if (x > bf.getWidth() - 1) {
                        x = 2 * bf.getWidth() - x - 2;
                    }
                    if (y < 0) {
                        y = Math.abs(y);
                    }
                    if (y > bf.getHeight() - 1) {
                        y = 2 * bf.getHeight() - y - 2;
                    }
                    Color color = new Color(bf.getRGB(x, y));

                    reds[j2][k] = weight * color.getRed();
                    greens[j2][k] = weight * color.getGreen();
                    blues[j2][k] = weight * color.getBlue();

                }
            }
            int red = getWeightedValue(reds);
            int green = getWeightedValue(greens);
            int blue = getWeightedValue(blues);

            res.setRGB(i, j, new Color(red, green, blue).getRGB());
            it++;
            index++;
        }
    }

    private int getWeightedValue(double[][] weightedColor) {
        double sum = 0;
        for (int i = 0; i < weightedColor.length; i++) {
            for (int j = 0; j < weightedColor.length; j++) {
                sum += weightedColor[i][j];
            }
        }
        return (int) sum;
    }
}
