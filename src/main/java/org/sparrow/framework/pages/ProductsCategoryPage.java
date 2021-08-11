package org.sparrow.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//класс, отображающий результат поиска в категориях
public class ProductsCategoryPage extends BasePage {

    @FindBy(xpath = "//div[@class='container']//div[@class='brand-zone-block']")
    private WebElement productsCategoryPage;

    public void isProductsCategoryPage() {
        //сделать проверку и return
    }
}
