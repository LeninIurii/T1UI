package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StatusCodes {
    ElementsCollection statusCode200Entity = $$(By.xpath("//a[contains(text(),'200')]"));
    ElementsCollection statusCode301Entity = $$(By.xpath("//a[contains(text(),'301')]"));
    ElementsCollection statusCode404Entity = $$(By.xpath("//a[contains(text(),'404')]"));
    ElementsCollection statusCode500Entity = $$(By.xpath("//a[contains(text(),'500')]"));
    SelenideElement statusTextEntity = $(By.xpath("//div[@class='example']/*[contains(text(),'%s')]"));

    public boolean textPage(String value){
       return $(By.xpath("//div[@class='example']/*[contains(text(),"+value+")]")).exists();
    }
    private void openStatusCodes200Entity() {
        statusCode200Entity.get(0).click();
    }

    private void openStatusCodes301Entity() {
        statusCode301Entity.get(0).click();
    }

    private void openStatusCodes404Entity() {
        statusCode404Entity.get(0).click();
    }

    private void openStatusCodes500Entity() {
        statusCode500Entity.get(0).click();
    }

    public void openTab(String name) {
        switch (name) {
            case "200":
                openStatusCodes200Entity();
                break;
            case "301":
                openStatusCodes301Entity();
                break;
            case "404":
                openStatusCodes404Entity();
                break;
            case "500":
                openStatusCodes500Entity();
                break;
        }
    }
}
