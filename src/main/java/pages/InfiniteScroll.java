package pages;

import base.BasicPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class InfiniteScroll extends BasicPage {
    SelenideElement element = $(By.xpath("//*[contains(text(), '%s')]"));

    @Step("Скролим до видимого элемента {text}")
    public void scrollPageToElement(String text, WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        while (!isTextVisible(text)) {
            js.executeScript("window.scrollBy(0,1000)");
        }
        Assert.assertTrue(isTextVisible(text));
    }

    @Step("Проверяем виден ли текст {text}")
    public static boolean isTextVisible(String text) {
        SelenideElement element = $(By.xpath("//*[contains(text(), '" + text + "')]"));
        return element.isDisplayed();
    }
}
