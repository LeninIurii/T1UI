package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddRemoveElements {
    SelenideElement addElement = $(By.xpath("//button[@onclick='addElement()']"));
    ElementsCollection deleteElement = $$(By.xpath("//button[@onclick='deleteElement()']"));
    ElementsCollection buttonCollection = $$(By.xpath("//div[@id='elements']/button"));


    private void clickAddElementEntity(int a) {
        for(int i = 1; a >= i; i++) {
            addElement.click();
            System.out.println(buttonCollection.get(i-1).getText());
        }
    }
    @Step("Выводим оставшееся колличество кнопок Delete и их текст")
    public void textEntityButtonDelete(){
        System.out.println("Колличество кнопок Delete: "+buttonCollection.size());
        for(int i=1;buttonCollection.size()>=i;i++){
            System.out.println("Текст кнопки №"+i+" "+buttonCollection.get(i-1).getText());
        }
    }
    @Step("Нажать на кнопку Delete {a} раз")
    public void clickRandomButton(int a){
        for(int i=1; a>=i ;i++){
            deleteElement.get(i-1).click();
        }
    }

    private void clickDeleteEntity() {
        deleteElement.get(0).click();
    }

    @Step("Нажать кнопку {button} {a} раз")
    public void clickButton(String button, int a) {
        switch (button) {
            case "Add Element":
                clickAddElementEntity(a);
                break;
            case "Delete":
                clickDeleteEntity();
                break;
            default:
                throw new IllegalArgumentException("Недопустимое значение button: "+button);

        }

    }

    @Step("Необходимое колличество элементов Delete {a}")
    public void nedDell(int a){
        System.out.println(deleteElement.size());
       do{
           if ((deleteElement.size() > a)) {
               deleteElement.get(0).click();
           }

           if(deleteElement.size() < a) {
               addElement.click();
           }
       }while (deleteElement.size()!=(a));
    }

    @Step("Проверка. Колличество элементов деллит совпадает сожидаемым {a}")
    public void entityDell(int a){
        Assert.assertEquals(deleteElement.size(),a,"Oh noyy, it is going to crush your computer мало делитов");

    }

}
