package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.*;

class CartDataTest {

    CartDataGenerator cartDataGenerator = new CartDataGenerator();
    CartData cartData = new CartData();

    @Test
    void shouldBeSuccessTest() {
        cartData.setCity(cartDataGenerator.cityGenerator());
        cartData.setDate(cartDataGenerator.dateFutureGenerator());
        cartData.setName(cartDataGenerator.nameGenerator());
        cartData.setPhoneNumber(cartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue(cartData.getPhoneNumber());
        $(".checkbox__text").click();
        $("button.button").shouldHave(text("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible).waitUntil(Condition.visible,15000);
    }

    @Test
    void shouldBePreviousDateTest() {
        cartData.setCity(cartDataGenerator.cityGenerator());
        cartData.setDate(cartDataGenerator.datePreviousGenerator());
        cartData.setName(cartDataGenerator.nameGenerator());
        cartData.setPhoneNumber(cartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue(cartData.getPhoneNumber());
        $(".checkbox__text").click();
        $("button.button").shouldHave(text("Запланировать")).click();
        $(withText("Заказ на выбранную дату невозможен"));
    }

        @Test
        void shouldBeWrongNameTest() {
            cartData.setCity(cartDataGenerator.cityGenerator());
            cartData.setDate(cartDataGenerator.dateFutureGenerator());
            cartData.setName(cartDataGenerator.nameGenerator());
            cartData.setPhoneNumber(cartDataGenerator.phoneGenerator());
            open("http://localhost:9999");
            $("[placeholder='Город']").setValue(cartData.getCity());
            $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
            $("[placeholder='Дата встречи']").setValue(cartData.getDate());
            $("input[type='text'][name='name']").setValue("Pyotr Petrov");
            $("input[type='tel'][name='phone']").setValue(cartData.getPhoneNumber());
            $(".checkbox__text").click();
            $("button.button").shouldHave(text("Запланировать")).click();
            $(withText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

    @Test
    void shouldBeWrongPhoneTest() {
        cartData.setCity(cartDataGenerator.cityGenerator());
        cartData.setDate(cartDataGenerator.dateFutureGenerator());
        cartData.setName(cartDataGenerator.nameGenerator());
        cartData.setPhoneNumber(cartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue("");
        $(".checkbox__text").click();
        $("button.button").shouldHave(text("Запланировать")).click();
        $(withText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeNoCheckboxTest() {
        cartData.setCity(cartDataGenerator.cityGenerator());
        cartData.setDate(cartDataGenerator.dateFutureGenerator());
        cartData.setName(cartDataGenerator.nameGenerator());
        cartData.setPhoneNumber(cartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue("+79810000000");
        $("button.button").shouldHave(text("Запланировать")).click();
        $(withText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldBeSameDataTest() {
        cartData.setCity(cartDataGenerator.cityGenerator());
        cartData.setDate(cartDataGenerator.dateFutureGenerator());
        cartData.setName(cartDataGenerator.nameGenerator());
        cartData.setPhoneNumber(cartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue("10.05.2020");
        $("input[type='text'][name='name']").setValue("Иван Иванов");
        $("input[type='tel'][name='phone']").setValue("+79810000000");
        $(".checkbox__text").click();
        $("button.button").shouldHave(text("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible).waitUntil(visible,10000);
        $("button.button").shouldHave(text("Перепланировать")).click();
        $(withText("Успешно!"));

    }

}