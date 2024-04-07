package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.UtilsMethods;

import static com.codeborne.selenide.Selenide.$;

public class Inputs {
    SelenideElement input=$(By.xpath("//input"));

    public void sendKey(){
        input.sendKeys(UtilsMethods.randomNumberString(5));
        System.out.println("Значение элемента Input "+input.getAttribute("value"));
    }
}
