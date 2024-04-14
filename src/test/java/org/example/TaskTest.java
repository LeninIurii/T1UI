package org.example;


import base.Browser;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.openqa.selenium.Keys;
import pages.ContextMenu;
import pages.DragAndDrop;
import pages.InfiniteScroll;
import pages.KeyPresses;
import step.AdminEntitiesStep;

import java.util.function.Function;
import java.util.stream.Stream;

class TaskTest extends Browser {

    private static AdminEntitiesStep adminEntitiesStep = new AdminEntitiesStep();

    private static DragAndDrop dragAndDrop = new DragAndDrop();
    private static ContextMenu contextMenu = new ContextMenu();
    private static InfiniteScroll infiniteScroll = new InfiniteScroll();
    private static KeyPresses keyPresses = new KeyPresses();

    @Description("Перейти на страницу Drag and Drop. Перетащить элемент A на элемент B. " +
            "Задача на 10 баллов – сделать это, не прибегая к методу DragAndDrop();" +
            "Проверить, что элементы поменялись местами")
    @Test
    void withoutDragAndDrop() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Drag and Drop");
        dragAndDrop.dragAndDrop("A", "B", webDriver);
    }

    @Description("Перейти на страницу Context menu. " +
            "Нажать правой кнопкой мыши на отмеченной области и проверить, " +
            "что JS Alert имеет ожидаемый текст.")
    @Test
    void jsAlert() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Context menu");
        contextMenu.expectedJAlert("You selected a context menu", webDriver);
    }

    @Description("Перейти на страницу Infinite Scroll. " +
            "Проскролить страницу до текста «Eius», проверить, что текст в поле зрения.")
    @Test
    void infiniteScrollTest() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Infinite Scroll");
        infiniteScroll.scrollPageToElement("Eius", webDriver);
    }


    @Description("Перейти на страницу Key Presses. " +
            "Нажать по 10 латинских символов, клавиши Enter, Ctrl, Alt, Tab. " +
            "Проверить, что после нажатия отображается всплывающий текст снизу, соответствующий конкретной клавише.")
    @TestFactory
    Stream<DynamicTest> dynamicTestsInputStream() {
        webDriver.get("https://the-internet.herokuapp.com");
        adminEntitiesStep.openPage("Key Presses");

        Stream<String> inputStream = Stream.of(
                Keys.ENTER.toString(),
                Keys.ALT.toString(),
                Keys.TAB.toString(),
                Keys.CONTROL.toString(),
                "A",
                "C",
                "E",
                "F",
                "L",
                "P",
                "U",
                "V",
                "Z"
        );

        Function<String, String> displayNameGenerator = str -> "Test for input: " + str;

        ThrowingConsumer<String> testExecutor = str -> {
            keyPresses.input(str);

        };

        return DynamicTest.stream(inputStream, displayNameGenerator, testExecutor);
    }

}