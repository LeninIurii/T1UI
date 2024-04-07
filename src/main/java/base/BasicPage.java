package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class BasicPage {
    protected Logger log;
    protected Browser browser;

    protected boolean isElementDisplay(String locator) {
        return this.browser.isElementDisplay(By.xpath(locator));
    }

    protected void ScrollPageToElement(String locator) {
        this.browser.ScrollPageToElement(By.xpath(locator));
    }

    protected void click(By by) {
        this.browser.waitForAjaxQueryComplete(this.browser.getImplicitlyWait());
        this.browser.getWebDriver().findElement(by).click();
    }

    protected void click(String locator) {
        this.browser.waitForAjaxQueryComplete(this.browser.getImplicitlyWait());
        this.browser.getWebDriver().findElement(By.xpath(locator)).click();
    }


    protected void click(String locator, String logMessage) {
        if (!logMessage.equals("")) {
            this.log.info("Клик по [" + logMessage + "].");
        }

        this.browser.waitForAjaxQueryComplete(this.browser.getImplicitlyWait());
        this.browser.getWebDriver().findElement(By.xpath(locator)).click();
    }

}
