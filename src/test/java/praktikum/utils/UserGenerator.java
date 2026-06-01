package praktikum.utils;

import praktikum.model.User;

import java.util.Random;

public class UserGenerator {

    public static String generateEmail() {
        Random random = new Random();
        return "user" + random.nextInt(100000) + "@yandex.ru";
    }

    public static User generateUser() {

        String email = generateEmail();
        String password = "123456";
        String name = "TestUser";

        return new User(email, password, name);
    }
}