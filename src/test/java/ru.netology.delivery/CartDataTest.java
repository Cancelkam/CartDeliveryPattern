package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.*;

class CartDataTest {

    @Test
    void shouldBeSuccessTest() {
        CartData cartData = new CartData(CartDataGenerator.cityGenerator(), CartDataGenerator.dateGenerator(), CartDataGenerator.nameGenerator(), CartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue(cartData.getPhoneNumber());
        $(".checkbox__text").click();
        $("button.button").shouldHave(text("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
    }

    @Test
    void shouldGetErrorNotificationIfSendPreviousOr3daysFuture() {
        CartData cartData = new CartData(CartDataGenerator.cityGenerator(), CartDataGenerator.wrongDateGenerator(), CartDataGenerator.nameGenerator(), CartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue(cartData.getPhoneNumber());
        $(".checkbox__text").click();
        $("button.button").shouldHave(text("Запланировать")).click();
        $(".input_invalid").shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldGetErrorNotificationIfSendWrongName() {
        CartData cartData = new CartData(CartDataGenerator.cityGenerator(), CartDataGenerator.dateGenerator(), CartDataGenerator.wrongNameGenerator(), CartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue(cartData.getPhoneNumber());
        $(".checkbox__text").click();
        $("button.button").shouldHave(text("Запланировать")).click();
        $(".input_invalid").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

    @Test
    void shouldGetErrorNotificationIfSendWrongPhone() {
        CartData cartData = new CartData(CartDataGenerator.cityGenerator(), CartDataGenerator.dateGenerator(), CartDataGenerator.nameGenerator(), CartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue("");
        $(".checkbox__text").click();
        $("button.button").shouldHave(text("Запланировать")).click();
        $(".input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetErrorNotificationIfNoCheckbox() {
        CartData cartData = new CartData(CartDataGenerator.cityGenerator(), CartDataGenerator.dateGenerator(), CartDataGenerator.nameGenerator(), CartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue("+79810000000");
        $("button.button").shouldHave(text("Запланировать")).click();
        $(".input_invalid").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldSuccessSendFormIfPlanAndReplanMeeting() {
        CartData cartData = new CartData(CartDataGenerator.cityGenerator(), CartDataGenerator.dateGenerator(), CartDataGenerator.nameGenerator(), CartDataGenerator.phoneGenerator());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cartData.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(CONTROL, "a"), DELETE);
        $("[placeholder='Дата встречи']").setValue(cartData.getDate());
        $("input[type='text'][name='name']").setValue(cartData.getName());
        $("input[type='tel'][name='phone']").setValue(cartData.getPhoneNumber());
        $(".checkbox__text").click();
        $("button.button").shouldHave(exactText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
        $("button.button").shouldHave(exactText("Запланировать")).click();
        $("div[data-test-id='replan-notification']").shouldBe(visible);
        $$(".button__content").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);;
    }
}