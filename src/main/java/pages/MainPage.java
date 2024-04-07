package pages;

import base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends BasePage {

    ElementsCollection checkboxesEntity = $$(By.xpath("//a[text()='Checkboxes']"));
    ElementsCollection dropdownEntity = $$(By.xpath("//a[text()='Dropdown']"));
    ElementsCollection disappearingElementsEntity = $$(By.xpath("//a[text()='Disappearing Elements']"));
    ElementsCollection inputsElementsEntity = $$(By.xpath("//a[text()='Inputs']"));
    ElementsCollection hoversElementsEntity = $$(By.xpath("//a[text()='Hovers']"));
    ElementsCollection notificationMessageElementsEntity = $$(By.xpath("//a[text()='Notification Messages']"));
    ElementsCollection addRemoveElementsEntity = $$(By.xpath("//a[text()='Add/Remove Elements']"));
    ElementsCollection statusCodesElementsEntity = $$(By.xpath("//a[text()='Status Codes']"));

    private void openCheckboxesEntity() {
        checkboxesEntity.get(0).click();
    }

    private void openDropdownEntity() {
        dropdownEntity.get(0).click();
    }

    private void openDisappearingElementsEntity() {
        disappearingElementsEntity.get(0).click();
    }

    private void openInputsElementsEntity() {
        inputsElementsEntity.get(0).click();
    }

    private void openHoversElementsEntity() {
        hoversElementsEntity.get(0).click();
    }

    private void openNotificationMessageElementsEntity() {
        notificationMessageElementsEntity.get(0).click();
    }

    private void openAddRemoveElementsEntity() {
        addRemoveElementsEntity.get(0).click();
    }
    private void openStatusCodesElementsEntity() {
        statusCodesElementsEntity.get(0).click();
    }

    public void openTab(String name) {
        switch (name) {
            case "Checkboxes":
                openCheckboxesEntity();
                break;
            case "Dropdown":
                openDropdownEntity();
                break;
            case "Disappearing Elements":
                openDisappearingElementsEntity();
                break;
            case "Inputs":
                openInputsElementsEntity();
                break;
            case "Hovers":
                openHoversElementsEntity();
                break;
            case "Notification Messages":
                openNotificationMessageElementsEntity();
                break;
            case "Add/Remove Elements":
                openAddRemoveElementsEntity();
                break;
            case "Status Codes":
                openStatusCodesElementsEntity();
                break;
        }

    }
}
