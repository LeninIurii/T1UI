package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class NotificationMessage {
    SelenideElement click = $(By.xpath("//a[text()='Click here']"));
    SelenideElement notice = $(By.xpath("//div[@class='flash notice']"));
    SelenideElement notice2 = $(By.xpath("//*[contains(text(),'Action successful')]"));
    SelenideElement close = $(By.xpath("//a[@class='close']"));

    @Step("Кликать до тех пор, пока не покажется уведомление Action successful")
    public void clickKurlic(WebDriver driver)  {
        Actions actions = new Actions(driver);
        do {
            if(!notice2.exists()){
            if(notice.exists())  actions.click(close).perform();
            actions.click(click).perform();}
        }
        while (!notice2.exists());
    }

    public void notificationSuccess(){
//        Assert.assertEquals(notice2.shouldBe(Condition.visible).text(),"Action successful\n×");
        Assert.assertTrue(notice2.exists());
    }

}
