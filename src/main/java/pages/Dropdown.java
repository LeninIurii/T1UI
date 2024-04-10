package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Dropdown {
    ElementsCollection LOCATOR_DropdownList = $$(By.xpath("//select[@id='dropdown']"));
    SelenideElement LOCATOR_Selected = $(By.xpath("//option[@selected='selected']"));

    private void clickOption(int i) {
        $(By.xpath("//option[@value=" + i + "]")).click();
    }

    private String getOption(int i) {
        return $(By.xpath("//option[@value=" + i + "]")).getText();
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

    @Step("Получить элемент {name}")
    public String getOption(String name) {
        switch (name) {
            case "Option 1":
                return getOption(1);

            case "Option 2":
                return getOption(2);

        }
        infoTextDropdown(LOCATOR_Selected, name);
        return null;
    }

    @Step("Вводим в консоль текущий текст элемента dropdown")
    private void infoTextDropdown(SelenideElement lokator, String name) {
        System.out.println("В опции " + name + " содержиться текст \"" + lokator.getText() + "\"");
    }

    //todo кудато вынести
    @Step("ПРОВЕРКА. Dropdown элемента {checkbox} соответствует переданному значению {value}")
    public void checkElementsEquality(String dropdown, String value) {
        String actualText = getOption(dropdown);
        Assert.assertEquals(actualText, value);

    }
}
