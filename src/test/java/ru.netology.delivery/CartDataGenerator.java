package ru.netology.delivery;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CartDataGenerator {
    public static String cityGenerator() {
        String[] CityList = new String[] {"Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород", "Ростов-на-Дону", "Севастополь"};
        Random random = new Random();
        int city = random.nextInt(CityList.length);
        return CityList[city];
    }

    public static String dateGenerator() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        Random random = new Random();
        Instant dateInst = Instant.now().plus(3,ChronoUnit.DAYS).plus(random.nextInt(7), ChronoUnit.DAYS);
        return dateFormat.format(date.from(dateInst));
    }

    public static String wrongDateGenerator() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        Random random = new Random();
        Instant dateInst = Instant.now().plus(random.nextInt(2),ChronoUnit.DAYS);
        return dateFormat.format(date.from(dateInst));
    }

    public static String nameGenerator() {
        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public static String wrongNameGenerator() {
        Faker faker = new Faker(new Locale("en"));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public static String phoneGenerator() {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static String wrongPhoneGenerator() {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.letterify("qwerty");
        return phone;
    }
}
