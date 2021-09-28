package com.trian.common.utils.utils;

import java.util.Arrays;

/**
 * Ecg wave
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * Created At 27/09/2021
 */
//https://stackoverflow.com/questions/8835464/qrs-detection-in-java-from-ecg-byte-array
public class EcgUtils {
    // High pass filter
// y1[n] = 1/M * Sum[m=0, M-1] x[n-m]
// y2[n] = x[n - (M+1)/2]
    public static float[] highPass(int[] sig0, int nsamp) {
        float[] highPass = new float[nsamp];
        int M = 5; // M is recommended to be 5 or 7 according to the paper
        float constant = (float) 1/M;

        for(int i=0; i<sig0.length; i++) {
            float y1 = 0;
            float y2 = 0;

            int y2_index = i-((M+1)/2);
            if(y2_index < 0) {
                y2_index = nsamp + y2_index;
            }
            y2 = sig0[y2_index];

            float y1_sum = 0;
            for(int j=i; j>i-M; j--) {
                int x_index = i - (i-j);
                if(x_index < 0) {
                    x_index = nsamp + x_index;
                }
                y1_sum += sig0[x_index];
            }

            y1 = constant * y1_sum;
            highPass[i] = y2 - y1;

        }

        return highPass;
    }

    // Low pass filter; na n-to mesto zapiši kvadrat 30ih števil v oknu
    public static float[] lowPass(float[] sig0, int nsamp) {
        float[] lowPass = new float[nsamp];
        for(int i=0; i<sig0.length; i++) {
            float sum = 0;
            if(i+30 < sig0.length) {
                for(int j=i; j<i+30; j++) {
                    float current = sig0[j] * sig0[j];
                    sum += current;
                }
            }
            else if(i+30 >= sig0.length) {
                int over = i+30 - sig0.length;
                for(int j=i; j<sig0.length; j++) {
                    float current = sig0[j] * sig0[j];
                    sum += current;
                }
                for(int j=0; j<over; j++) {
                    float current = sig0[j] * sig0[j];
                    sum += current;
                }
            }

            lowPass[i] = sum;
        }

        return lowPass;

    }

    public static int[] QRS(float[] lowPass, int nsamp) {
        int[] QRS = new int[nsamp];

        double treshold = 0;

        for(int i=0; i<200; i++) {
            if(lowPass[i] > treshold) {
                treshold = lowPass[i];
            }
        }

        int frame = 250;

        for(int i=0; i<lowPass.length; i+=frame) {
            float max = 0;
            int index = 0;
            if(i + frame > lowPass.length) {
                index = lowPass.length;
            }
            else {
                index = i + frame;
            }
            for(int j=i; j<index; j++) {
                if(lowPass[j] > max) max = lowPass[j];
            }
            boolean added = false;
            for(int j=i; j<index; j++) {
                if(lowPass[j] > treshold && !added) {
                    QRS[j] = 1;
                    added = true;
                }
                else {
                    QRS[j] = 0;
                }
            }

            double gama = (Math.random() > 0.5) ? 0.15 : 0.20;
            double alpha = 0.01 + (Math.random() * ((0.1 - 0.01)));

            treshold = alpha * gama * max + (1 - alpha) * treshold;

        }

        return QRS;
    }
}

