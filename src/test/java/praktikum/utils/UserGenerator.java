package praktikum.utils;

import java.util.Random;

public class UserGenerator {

    public static String generateEmail() {
        Random random = new Random();
        return "user" + random.nextInt(100000) + "@yandex.ru";
    }
}