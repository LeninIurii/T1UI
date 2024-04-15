package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class KeyPresses {
    private static final Map<String, String> keyTextMap = new HashMap<>();

    static {
        keyTextMap.put(Keys.ENTER.toString(), "ENTER");
        keyTextMap.put(Keys.CONTROL.toString(), "CONTROL");
        keyTextMap.put(Keys.ALT.toString(), "ALT");
        keyTextMap.put(Keys.TAB.toString(), "TAB");
    }

    SelenideElement element = $(By.xpath("//p[@id='result']"));
    SelenideElement input = $(By.xpath("//input"));

    public void input(String str) throws InterruptedException {
        Thread.sleep(1000);
        clearInput();
        input.sendKeys(str);

        String keyText = keyTextMap.containsKey(str) ? getKeyText(str) : str;

        if (keyTextMap.containsKey(str)) {
            Assert.assertEquals(getText(), "You entered: " + keyText, "Input value does not match actual value");
        } else {
            Assert.assertEquals(getText(), "You entered: " + str, "Input value does not match actual value");
        }
    }

    public void clearInput() {
        input.clear();

    }

    public String getText() {
        return element.getText();
    }

    public static String getKeyText(String key) {
        return keyTextMap.getOrDefault(key, "Unknown key");
    }
}
