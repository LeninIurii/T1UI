package base;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Browser {
    protected Logger log;
    private String implicitlyTimeout;
    protected WebDriver webDriver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

    }

    /**
     * Настраиваем окна и подключаем WebDriverManager
     * с указанием запускаться перед каждым тестом
     **/
    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();

        WebDriverRunner.setWebDriver(webDriver);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }


    /**
     * Закрывание браузера после завершения каждого теста
     **/
    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    public Long getImplicitlyWait() {
        return Long.valueOf(this.implicitlyTimeout);
    }

    public boolean setImplicitlyWait(String seconds) {
        Boolean result = true;

        try {
            if (this.webDriver != null) {
                this.implicitlyTimeout = seconds.trim();
                this.webDriver.manage().timeouts().implicitlyWait(Long.valueOf(this.implicitlyTimeout), TimeUnit.SECONDS);
            } else {
                result = false;
            }
        } catch (Exception var4) {
            result = false;
        }

        return result;
    }

    public boolean isElementDisplay(final By by) {
        boolean res = true;
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    boolean result = false;

                    try {
                        result = d.findElement(by).isDisplayed();
                    } catch (Exception var4) {
                        result = false;
                    }

                    if (result) {
                        Browser.this.log.debug("displayed");
                    } else {
                        Browser.this.log.debug("not displayed");
                    }

                    return result;
                }
            });
        } catch (Exception var7) {
            res = false;
        }

        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return res;
    }

    public void ScrollPageToElement(By by) {
        WebElement element = this.webDriver.findElement(by);
        ((Locatable) element).getCoordinates().inViewPort();
    }

    public boolean waitForAjaxQueryComplete(long seconds) {
        boolean res = true;
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(seconds));

        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    long counter = (Long) ((JavascriptExecutor) d).executeScript("return window.jQuery.active", new Object[0]);
                    return counter == 0L;
                }
            });
            sleep(0.3D);
        } catch (Exception var6) {
            res = false;
        }

        return res;
    }

    public void sleep(double seconds) {
        try {
            log.debug("Wait [" + seconds + "] seconds...");
            Thread.sleep((long) (seconds * 1000.0D));
        } catch (Exception var4) {
        }
    }

    public WebDriver getWebDriver() {
        if (this.webDriver == null) {
            System.err.println("Шото с драйвером братан");
        }
        return this.webDriver;
    }
}

