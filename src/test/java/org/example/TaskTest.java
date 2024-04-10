package org.example;


import base.BasePage;
import base.Browser;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import pages.*;
import step.AdminEntitiesStep;
import utils.UtilsMethods;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest extends Browser {
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

    @ParameterizedTest
    @Description("Добавить проверки в задание Checkboxes из предыдущей лекции. " +
            "Проверять корректное состояние каждого чекбокса после каждого нажатия на него." +
            "Запустить тест с помощью @ParametrizedTest, изменяя порядок нажатия на чекбоксы с помощью одного параметра.")
    @CsvSource({
            "checkbox 2, false",
            "checkbox 2, false"
    })
    void checkboxesTest(String checkbox, String value) {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Checkboxes");

        checkboxes.clickCheckboxByName(checkbox, value);
        checkboxes.checkElementsEquality(checkbox, value);
    }

    @ParameterizedTest
    @Description("Добавить проверки в задание Dropdown из предыдущей лекции. " +
            "Проверять корректное состояние каждого dropDown после каждого нажатия на него.")
    @ValueSource(strings = {"Option 1", "Option 2"})
    void dropdownTest(String option) {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Dropdown");
        dropdown.clickOption(option);
        dropdown.checkElementsEquality(option, option);
    }

    @RepeatedTest(value = 10)
    @Description("Добавить проверки в задание Disappearing Elements из предыдущей лекции." +
            " Для каждого обновления страницы проверять наличие 5 элементов." +
            " Использовать @RepeatedTest.")
    void disappearingElementsTest() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Disappearing Elements");
        disappearingElements.checkDisappearingElementsEquality(5);
    }

    @Description("Добавить проверки в задание Inputs из предыдущей лекции. " +
            "Проверить, что в поле ввода отображается именно то число, которое было введено." +
            " Повторить тест 10 раз, используя @TestFactory, с разными значениями, вводимыми в поле ввода." +
            " Создать проверку негативных кейсов (попытка ввести в поле латинские буквы, спецсимволы, пробел до и после числа).")
    @TestFactory
    Stream<DynamicTest> dynamicTestsIntStream() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Inputs");

        Stream<String> inputStream = Stream.of(
                        UtilsMethods.randomNumberString(5),
                        UtilsMethods.randomNumberString(3) + " ",
                        " " + UtilsMethods.randomNumberString(3),
                        UtilsMethods.randomNumberString(42),
                        UtilsMethods.randomLatUpperString(3),
                        UtilsMethods.randomSpetialSymbolsString(1),
                        UtilsMethods.randomSpetialSymbolsString(1),
                        UtilsMethods.randomNumberString(10),
                        UtilsMethods.randomSpetialSymbolsString(1),
                        UtilsMethods.randomLatLowerString(4),
                        UtilsMethods.randomLatLowerString(42),
                        UtilsMethods.randomLatLowerString(42))
                .limit(10);

        Function<String, String> displayNameGenerator = str -> "Test for input: " + str;

        ThrowingConsumer<String> testExecutor = str -> {
            inputs.clearKeyValue();
            inputs.sendKeyValue(str);
            assertEquals(str, inputs.getKeyValue(), "Input value does not match actual value");
        };

        return DynamicTest.stream(inputStream, displayNameGenerator, testExecutor);
    }

    @Description("Добавить проверки в задание Hovers из предыдущей лекции. " +
            "При каждом наведении курсора, проверить, что отображаемый текст совпадает с ожидаемым." +
            " Выполнить тест с помощью @ParametrizedTest, в каждом тесте, указывая на какой элемент наводить курсор")
    @ParameterizedTest()
    @ValueSource(ints = {1, 2, 3})
    void hoversTest(int a) {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Hovers");
        hovers.moveOn(webDriver, a);
        Assert.assertNotNull(hovers.getHok(a));
    }

    @Description(
            "Добавить проверки в задание Notification Message из предыдущей лекции." +
                    " Добавить проверку, что всплывающее уведомление должно быть Successfull." +
                    " Если нет – провалить тест." +
                    " Использовать @RepeatedTest.")
    @RepeatedTest(5)
    void notificationMessageTest() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Notification Messages");
        notificationMessage.notificationSuccess();
    }

    @Description("Добавить проверки в задание Add/Remove Elements. " +
            "Проверять, что на каждом шагу остается видимым ожидаемое количество элементов. " +
            "Запустить тест три раза, используя @TestFactory, меняя количество созданий и удалений на 2:1, 5:2, 1:3 соответственно.")
    @TestFactory
    Stream<DynamicTest> dynamiElementsTest() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Add/Remove Elements");

        Stream<String> stream = Stream.of("2:1", "5:2", "1:3");

        Function<String, String> displayNameGenerator = str -> "Test for delete: " + str;

        ThrowingConsumer<String> testExecutor = str -> {
            String[] parts = str.split(":");
            if (parts.length == 2) {

                int firstValue = Integer.parseInt(parts[0]);
                int secondValue = Integer.parseInt(parts[1]);
                addRemoveElements.clickButton("Add Element", firstValue);
                addRemoveElements.nedDell(secondValue);
                addRemoveElements.entityDell(secondValue);

            } else throw new IllegalArgumentException("Неверный формат значения в stream и ты лупик теперь");
        };

        return DynamicTest.stream(stream, displayNameGenerator, testExecutor);
    }

    @Description(
            "Добавить проверки в задание Status Codes. " +
                    "Добавить Проверку, что переход был осуществлен на страницу с корректным статусом.")
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
    void statusGetText(String status) {
        statusCodes.openTab(status);
        statusCodes.textPage(status);
    }

}