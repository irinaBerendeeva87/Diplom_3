package org.example;

import java.util.Random;

public class User {
    private String email = "lunch" + new Random().nextInt(10000) + "@yandex.ru";
    private String password = "evening" + new Random().nextInt(10000);
    private String name = "sun" + new Random().nextInt(10000);

    public String getRandomName() {
        return this.name;
    }

    public  String getRandomEmail() {
        return this.email;
    }

    public  String getRandomPassword() {
        return this.password;
    }


}

