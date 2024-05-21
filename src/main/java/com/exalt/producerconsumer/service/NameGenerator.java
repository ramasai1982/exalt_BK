package com.exalt.producerconsumer.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NameGenerator {

    public String nameGenerator() {
        Random random = new Random();
        Random randomNumber = new Random();
        int length = randomNumber.nextInt(4, 7);
        String character = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(character.charAt(random.nextInt(character.length())));
        }

        // Ensure the first character is uppercase
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

        return sb.toString();
    }
}
