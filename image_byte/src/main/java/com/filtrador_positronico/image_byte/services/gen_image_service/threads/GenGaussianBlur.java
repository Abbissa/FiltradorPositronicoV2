package com.filtrador_positronico.image_byte.services.gen_image_service.threads;

import java.io.IOException;

import java.awt.image.BufferedImage;

public class GenGaussianBlur implements Runnable {

    private static final int N_THREADS = 4;
    BufferedImage img;
    BufferedImage bf;
    double variance;
    int radius;

    public GenGaussianBlur(BufferedImage bf, double variance, int radius) {
        this.bf = bf;
        this.variance = variance;
        this.radius = radius;
    }

    @Override
    public void run() {
        try {
            img = gaussianBlur(bf, variance, radius);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    private static BufferedImage gaussianBlur(BufferedImage bf, double variance, int radius)
            throws IOException {
        BufferedImage res = new BufferedImage(bf.getWidth(), bf.getHeight(), BufferedImage.TYPE_INT_RGB);

        double[][] weights = constructWeights(variance, radius);

        Thread[] threads = new Thread[N_THREADS];
        int iterations = res.getWidth() * res.getHeight() / N_THREADS;
        int index = 0;
        for (int i = 0; i < N_THREADS - 1; i++) {
            threads[i] = new Thread(new GaussianBlurThread(bf, res, radius, index, iterations, weights));
            threads[i].start();
            index += iterations;
        }

        threads[N_THREADS - 1] = new Thread(new GaussianBlurThread(bf, res, radius, index,
                res.getWidth() * res.getHeight() - index - 1, weights));
        threads[N_THREADS - 1].start();
        for (int i = 0; i < N_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return res;

    }

    private static double[][] constructWeights(double variance, int radius) {
        double weights[][] = new double[radius][radius];
        double sum = 0;
        for (int i = 0; i < radius; i++) {
            for (int j = 0; j < radius; j++) {
                weights[i][j] = gaussianModel(i - radius / 2, j - radius / 2, variance);
                sum += weights[i][j];
            }

        }
        for (int i = 0; i < radius; i++) {
            for (int j = 0; j < radius; j++) {
                weights[i][j] /= sum;
            }
        }
        return weights;
    }

    private static double gaussianModel(int x, int y, double variance) {

        return (1 / (2 * Math.PI * Math.pow(variance, 2))
                * Math.exp(-(Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(variance, 2))));
    }

}
