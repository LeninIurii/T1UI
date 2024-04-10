package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DisappearingElements {
    ElementsCollection elementsCollection = $$(By.xpath("//li"));

    @Step("Должно быть {name} элементов на странице")
    public void checkElements(int a, WebDriver driver) {

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (elementsCollection.size() >= a) {
                assertEquals(a, elementsCollection.size());
                return;
            }
            driver.navigate().refresh();
        }
        fail("Failed to display 5 elements after 10 attempts");
    }
    //todo кудато вынести
    @Step("ПРОВЕРКА. Disappearing Elements колличество эллементов соответствует переданному значению {size}")
    public void checkDisappearingElementsEquality(int size) {
        Assert.assertEquals(elementsCollection.size(), size);

    }

}


