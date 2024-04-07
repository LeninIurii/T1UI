package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Dropdown {
    ElementsCollection LOCATOR_DropdownList = $$(By.xpath("//select[@id='dropdown']"));
    SelenideElement LOCATOR_Selected = $(By.xpath("//option[@selected='selected']"));

    private void clickOption(int i) {
        $(By.xpath("//option[@value="+i+"]")).click();
    }

    @Step("Нажимаем на выпадающий список {name}")
    public void clickOption(String name) {
        switch (name) {
            case "Option 1":
                clickOption(1);
                break;
            case "Option 2":
                clickOption(2);
                break;
        }
        infoTextDropdown(LOCATOR_Selected, name);
    }

    @Step("Вводим в консоль текущий текст элемента dropdown")
    private void infoTextDropdown(SelenideElement lokator, String name) {
        System.out.println("В опции " + name + " содержиться текст \"" + lokator.getText() + "\"");
    }
}
