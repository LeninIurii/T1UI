package step;

import io.qameta.allure.Step;
import pages.MainPage;

public class AdminEntitiesStep {
private static MainPage mainPage=new MainPage();
    @Step("Пользователь нажимает на {name}")
    public void openPage(String name) {
        mainPage.openTab(name);
    }
}
