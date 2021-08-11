package org.sparrow.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.sparrow.framework.model.Product;
import org.sparrow.framework.model.Warranties;
import org.sparrow.framework.pages.blocks.HeaderBlock;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    private HeaderBlock headerBlock = pageManager.getHeaderBlock();
    private Product product;

    @FindBy(xpath = "//div[@class='container product-card']")
    private WebElement productPage;

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@class='product-card-top__buy']//div[contains(@class, 'product-buy__price-wrap')]/div[contains(@class, 'product-buy__price')]")
    private WebElement price;

    @FindBy(xpath = "//div[@class='product-card-top__buy']//button[contains(@class, 'buy-btn')]")
    private WebElement buyBtn;

    @FindBy(xpath = "//div[@class='product-card-top__code']")
    private WebElement productCode;

    @FindBy(xpath = "//div[@class='additional-sales-tabs__titles-wrap']/div")
    private List<WebElement> additionalSalesTabsList;

    //контейнер для субменю вкладок
    @FindBy(xpath = "//div[@class='additional-sales-tabs__content-wrap']//input[@type='radio']/..")
    private List<WebElement> warrantyRadioBtns;


    //выбрать вкладку из меню доп.опций (в дз - гарантия)
    public ProductPage selectTabFromAdditionalSalesTabsList(String tabName) {

        for (WebElement tab : additionalSalesTabsList) {
            if (formatString(tab.getText()).contains(formatString(tabName))) {
                waitUtilElementToBeClickable(tab).click();
                return this;
            }
        }
        Assertions.fail("Меню '" + tabName + "' не было найдено на странице карточки товара");
        return this;
    }

    //выбрать гарантию и "запомнить" в продукт, выполняется проверка соответствия выбранной гарантии
    public ProductPage selectWarrantyRadioBtn(String value) {
        List<Product.Warranty> warranties = new ArrayList<>();

        for (WebElement radioBtn : warrantyRadioBtns) {
            waitUtilElementToBeVisible(radioBtn);

            if (formatString(radioBtn.getText()).contains(formatString(value))) {
                waitUtilElementToBeClickable(radioBtn).click(); //TODO нет проверки, если совпадений > 1
                String[] str = radioBtn.getText().split("\n");
                warranties.add(new Product.Warranty(Warranties.fromString(str[0]), getIntFromString(str[1])));
                product.setWarrantyList(warranties);
                return this;
            }
        }
        Assertions.fail("RadioBtn '" + value + "' не найдена");
        return this;
    }

    //TODO убрать Thread.sleep - сейчас он позволяет дождаться изменения цены в корзине для .checkSum() в header
    //кликнуть "купить" товар
    public ProductPage clickBuyProductBtn() {
        waitUtilElementToBeClickable(buyBtn).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    //запоминаем продукт (без гарантии)
    public ProductPage memorizeProduct() {
        product = new Product(title.getText(), getIntFromString(productCode.getText()), getIntFromString(price.getText()));
        setProduct(product);
        return this;
    }

    //проверка, что это страница карточки товара
    public ProductPage isProductPage() {
        waitUtilElementToBeVisible(productPage);
        String urlSamePart = "https://www.dns-shop.ru/product/";

        Assertions.assertAll("НЕ страница списка товаров (ProductsListPage)",
                () -> Assertions.assertTrue(productPage.isDisplayed()),
                () -> Assertions.assertTrue(driverManager.getDriver().getCurrentUrl().contains(urlSamePart))
        );
        return this;
    }

    //переход к header блоку
    public HeaderBlock toHeaderBlock() {
        return headerBlock.toHeaderBlock();
    }

    //клик по корзине в header
    public CartPage clickToCartPageHeader() {
        return headerBlock.clickCartHeader();
    }
}