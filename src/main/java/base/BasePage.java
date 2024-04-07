package base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class BasePage extends BasicPage {
    @Step("Нажать на элемент")
    public void click(String locator) {
        ScrollPageToElement(locator);
        click(By.xpath(locator));
    }
}
