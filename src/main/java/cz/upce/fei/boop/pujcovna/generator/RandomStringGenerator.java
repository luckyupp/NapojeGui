/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.generator;

import java.util.Random;

/**
 *
 * @author matam
 */
public class RandomStringGenerator {

    Random random = new Random();

    public String generujRandomString(int length) {
        String znaky = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(znaky.length());
            char randomChar = znaky.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
