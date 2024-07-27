package com.shorter.shorter.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class UrlUtils {
    public StringBuilder generateAleatoryUrl() {
        char[] characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder url = new StringBuilder(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char aleatoryChar = characters[random.nextInt(characters.length)];
            url.append(aleatoryChar);
        }
        return url;
    }
}
