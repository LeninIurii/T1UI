package utils;

import java.util.Random;

public class UtilsMethods {
    private static String randomString(String characters, int length) {
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
            text[i] = characters.charAt(random.nextInt(characters.length()));

        return new String(text);
    }

    public static String randomNumberString(int length) {
        return randomString("0123456789", length);
    }

    public static String randomCyrLowerString(int length) {
        return randomString("абвгдеёжзийклмнопрстуфхцчшщъыьэюя", length);
    }

    public static String randomLatLowerString(int length) {
        return randomString("abcdefghijklmnopqrstuvwxyz", length);
    }

    public static String randomLatUpperString(int length) {
        return randomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }
    public static String randomSpetialSymbolsString(int length) {
        return randomString("@#$%^&*! ", length);
    }

}
