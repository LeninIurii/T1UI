package pages;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class Checkboxes extends BasePage {
    SelenideElement LOCATOR_CheckedCheckbox1 = $(By.xpath("//form/input[1][@checked]"));
    SelenideElement LOCATOR_CheckedCheckbox2 = $(By.xpath("//form/input[2][@checked]"));
    SelenideElement LOCATOR_Checkbox1 = $(By.xpath("//input[@type='checkbox'][1]"));
    SelenideElement LOCATOR_Checkbox2 = $(By.xpath("//input[@type='checkbox'][2]"));

    @Step("Перевести чекбокс '{value}' в состояние '{condition}'")
    public void clickCheckboxByName(String value, String condition) {
        switch (value) {
            case ("checkbox 1"):
                switch (condition) {
                    case ("true"):
                        if (!(LOCATOR_CheckedCheckbox1.exists())) {
                            LOCATOR_Checkbox1.click();
                        }
                        break;

                    case ("false"):
                        if (!(LOCATOR_Checkbox1.exists())) {
                            LOCATOR_CheckedCheckbox1.click();
                        }
                        break;
                }
                infoAttribute(LOCATOR_Checkbox1, value, "checked");
                break;
            case ("checkbox 2"):
                switch (condition) {
                    case ("true"):
                        if (!(LOCATOR_CheckedCheckbox2.exists())) {
                            LOCATOR_Checkbox2.click();
                        }
                        break;
                    case ("false"):
                        if (!(LOCATOR_Checkbox2.exists())) {
                            LOCATOR_CheckedCheckbox2.click();
                        }
                        break;
                }
                infoAttribute(LOCATOR_Checkbox2, value, "checked");

                break;
            default:
                throw new IllegalArgumentException("Неверное значение введеного поля: " + value);
        }

    }

    @Step("Получить состояние чекбокса '{checkbox}'")
    public String getCheckboxValue(String checkbox) {
        switch (checkbox) {
            case ("checkbox 1"):
              return LOCATOR_CheckedCheckbox1.exists() ? "true" : "false";

            case ("checkbox 2"):
                return  LOCATOR_CheckedCheckbox2.exists() ? "true" : "false";
            default:
                throw new IllegalArgumentException("Неверное значение введеного поля: " + checkbox);
        }

    }

    @Step("Получить состояние атрибут {attribute} по локатору {lokator} с именем {value}")
    private void infoAttribute(SelenideElement lokator, String value, String attribute) {
        String checkbox1Checked = String.valueOf(lokator.getAttribute(attribute));
        System.out.println("Состояне атрибута \"" + attribute + "\" у " + value + " : " + checkbox1Checked);
    }

    //todo кудато вынести
    @Step("ПРОВЕРКА. Checkbox элемента {checkbox} соответствует переданному значению {value}")
    public void checkElementsEquality(String checkbox, String value) {
        String actualText = getCheckboxValue(checkbox);
        Assert.assertEquals(actualText, value);

    }

}

