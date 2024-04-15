package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class ContextMenu {
    SelenideElement hotSpot = $(By.xpath("//div[@id='hot-spot']"));

    public void expectedJAlert(String expectedText, WebDriver webDriver) {
        Actions actions = new Actions(webDriver);

        actions.contextClick(hotSpot).perform();

        Alert alert = webDriver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        Assert.assertEquals(alertText, expectedText);

    }
}
