package praktikum.utils;

import java.util.Random;

public class UserGenerator {

    public static String generateEmail() {
        Random random = new Random();
        return "user" + random.nextInt(10000) + "@yandex.ru";
    }
}