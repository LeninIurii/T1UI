package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DragAndDrop {

    SelenideElement elementA = $(By.xpath("//div[@id='column-a']"));
    SelenideElement elementB = $(By.xpath("//div[@id='column-b']"));
    ElementsCollection elements = $$(By.xpath("//div[@id='columns']//header"));


    private WebElement getElement(String value) {
        switch (value) {
            case "A":
                return elementA;

            case "B":
                return elementB;

            default:
                throw new IllegalArgumentException("Неверное имя элемента " + value);
        }
    }

    @Step("Взять элемент {a} и перетащить на элемент {b}")
    public void dragAndDrop(String a, String b, WebDriver webDriver) {
        Actions actions = new Actions(webDriver);
        String textA = getElement(a).getText();
        String textB = getElement(b).getText();
        actions.clickAndHold(getElement(a)).moveToElement(getElement(b)).release().build().perform();

        Assert.assertNotEquals(getElement(a).getText(), textA);
        Assert.assertNotEquals(getElement(b).getText(), textB);

    }
}
