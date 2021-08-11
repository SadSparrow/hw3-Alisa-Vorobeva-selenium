package org.sparrow.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsListPage extends BasePage {

    @FindBy(xpath = "//div[@class='container category-child']")
    private WebElement productsListPage;

    @FindBy(xpath = "//div[@data-id='product']")
    private List<WebElement> productsList;


    //какую проверку оставить? или объединить как в ProductPage?
    public ProductsListPage isProductsListPageByXpath() {
        waitUtilElementToBeVisible(productsListPage);
        Assertions.assertTrue((productsListPage.isDisplayed()), "НЕ страница списка товаров (ProductsListPage)");
        return this;
    }

    public ProductsListPage isProductsListPageByUrl() {
        String urlSamePart = "https://www.dns-shop.ru/search/?";
        Assertions.assertTrue(driverManager.getDriver().getCurrentUrl().contains(urlSamePart), "НЕ страница списка товаров (ProductsListPage)");
        return this;
    }

    //выбрать первый продукт
    public ProductPage clickFirstProduct() {
        productsList.get(0).click();
        return pageManager.getProductPage().isProductPage();
    }

    //TODO сделать полную обработку, поиск
    public void getProduct() {
        WebElement product;
        if (productsList != null) {
            for (WebElement prod : productsList) {
                WebElement el = prod.findElement(By.xpath(".//a[contains(@class, 'name')]/span"));
                System.out.println("1. " + el.getText());
            }
        }
    }
}