package com.cv.utils;

import java.util.Random;

public class GenerateRandomString {
    public static final String SOURCES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String generateString() {
        char[] text = new char[50];
        Random random = new Random();
        for (int i = 0; i < 50; i++) {

            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }
    public static String generateString(int k) {
        char[] text = new char[k];
        Random random = new Random();
        for (int i = 0; i < k; i++) {

            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }
}