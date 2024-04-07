package org.example;


import base.BasePage;
import base.Browser;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import pages.*;
import step.AdminEntitiesStep;

public class TaskTest extends Browser {
    private static AdminEntitiesStep adminEntitiesStep = new AdminEntitiesStep();
    private static BasePage basePage = new BasePage();
    private static Checkboxes checkboxes = new Checkboxes();
    private static Dropdown dropdown = new Dropdown();
    private static DisappearingElements disappearingElements = new DisappearingElements();
    private static Inputs inputs = new Inputs();
    private static Hovers hovers = new Hovers();
    private static NotificationMessage notificationMessage = new NotificationMessage();
    private static AddRemoveElements addRemoveElements = new AddRemoveElements();
    private static StatusCodes statusCodes = new StatusCodes();


    @Description(
            "Перейти на страницу Checkboxes. " +
                    "Выделить первый чекбокс, снять выделение со второго чекбокса. " +
                    "Вывести в консоль состояние атрибута checked для каждого чекбокса."
    )
    @Test
    void checkboxesTest() throws InterruptedException {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Checkboxes");
        checkboxes.clickCheckboxByName("checkbox 1", "true");
        checkboxes.clickCheckboxByName("checkbox 2", "false");

    }

    @Description("Перейти на страницу Dropdown." +
            " Выбрать первую опцию, " +
            "вывести в консоль текущий текст элемента dropdown," +
            " выбрать вторую опцию," +
            " вывести в консоль текущий текст элемента dropdown.")
    @Test
    void dropdownTest() throws InterruptedException {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Dropdown");
        dropdown.clickOption("Option 1");
        dropdown.clickOption("Option 2");

    }

    @Description("Перейти на страницу Disappearing Elements. " +
            "Добиться отображения 5 элементов," +
            " максимум за 10 попыток, если нет, провалить тест с ошибкой.")
    @Test
    void disappearingElementsTest() throws InterruptedException {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Disappearing Elements");
        disappearingElements.checkElements(5, webDriver);
    }

    @Description("Перейти на страницу Inputs. Ввести любое случайное число от 1 до 10 000. Вывести в консоль значение элемента Input.")
    @Test
    void inputsTest() throws InterruptedException {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Inputs");
        inputs.sendKey();
    }

    @Description("Перейти на страницу Hovers. " +
            "Навести курсор на каждую картинку." +
            " Вывести в консоль текст, который появляется при наведении.")
    @Test
    void hoversTest() throws InterruptedException {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Hovers");
        hovers.moveOn(webDriver);
    }

    @Description(" Перейти на страницу Notification Message. " +
            "Кликать до тех пор, пока не покажется уведомление Action successful. " +
            "После каждого неудачного клика закрывать всплывающее уведомление.")
    @Test
    void notificationMessageTest() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Notification Messages");
        notificationMessage.clickKurlic(webDriver);
    }

    @Description("Перейти на страницу Add/Remove Elements. " +
            "Нажать на кнопку Add Element 5 раз." +
            "С каждым нажатием выводить в консоль текст появившегося элемента." +
            " Нажать на разные кнопки Delete три раза. " +
            "Выводить в консоль оставшееся количество кнопок Delete и их тексты.")
    @Test
    void addRemoveElementsTest() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Add/Remove Elements");
        addRemoveElements.clickButton("Add Element", 5);
        addRemoveElements.clickRandomButton(3);
        addRemoveElements.textEntityButtonDelete();
    }

    @Description("Перейти на страницу Status Codes." +
            " Кликнуть на каждый статус в новом тестовом методе, вывести на экран текст после перехода на страницу статуса.\" +\n" +
            "Добавить плагин allure, так, чтобы формировался allure отчет по проведенным тестам.\"")
    @Test
    void statusCodesElementsTest() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Status Codes");
        statusGetText("200");
        webDriver.navigate().back();
        statusGetText("301");
        webDriver.navigate().back();
        statusGetText("404");
        webDriver.navigate().back();
        statusGetText("500");
        webDriver.navigate().back();
    }

@Step("Получаем текст по коду {status}")
    void statusGetText(String status){
        statusCodes.openTab(status);
        statusCodes.textPage();
    }

}