package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$$;

public class Hovers {
    ElementsCollection collection = $$(By.xpath("//div[@class='figure']/img"));
    ElementsCollection elementCollection = $$(By.xpath("//div[@class='figcaption']/h5"));


    @Step("Получаем текст при наведении")
    public void moveOn(WebDriver webDriver) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; collection.size() >= i; i++) {
            if (i >= collection.size()) {
                return;
            }
            actions.moveToElement(collection.get(i)).perform();
            System.out.println(elementCollection.get(i).shouldBe(Condition.visible).getText());
        }
    }
}
