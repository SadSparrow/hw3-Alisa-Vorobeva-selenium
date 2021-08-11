package org.sparrow.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sparrow.framework.model.Product;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement title;

    private final String parentPriceAmountXpath = "//div[@class='total-amount__label']";

    @FindBy(xpath = parentPriceAmountXpath + "//span[@class='price__current']")
    private WebElement totalPrice;

    @FindBy(xpath = parentPriceAmountXpath + "//div[@class='total-amount__count']")
    private WebElement totalAmount;

    private final String cartItemXpath = "//div[@class='cart-items__content-container']";
    @FindBy(xpath = cartItemXpath)
    private List<WebElement> products;

    @FindBy(xpath = cartItemXpath + "//i[@class='count-buttons__icon-plus']")
    private WebElement countBtnPlus;

    @FindBy(xpath = "//span[@class='restore-last-removed']")
    private List<WebElement> restoreBtn;

    //увеличение количества товара на count значение по его коду товара
    public CartPage increaseQuantity(int count, int codeProduct) {
        //TODO нужно ли положить тест, если i < 0?
        scrollToTopJs();

        for (WebElement product : products) {
            int code = getIntFromString(product.findElement(By.xpath(".//div[@class='cart-items__product-code']/div")).getText());
            if (code == codeProduct) {

                for (int i = 0; i < count; i++) {
                    String startingPrice = totalPrice.getText();
                    waitUtilElementToBeClickable(countBtnPlus).click();
                    wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(totalPrice, "innerText", startingPrice)));
                }

                int price = getIntFromString(product.findElement(By.xpath(".//span[@class='price__current']")).getText());
                int productPrice = getProductByCode(codeProduct).getPrice();
                Assertions.assertEquals(productPrice + productPrice * count, price, "Сумма не совпадает");
            }
        }
        return this;
    }

    //осуществляется проверка цены каждого товара в корзине, гарантия, общая стоимость товаров в корзине
    public CartPage checkTotal() {

        for (WebElement product : products) {
            String code = product.findElement(By.xpath(".//div[@class='cart-items__product-code']/div")).getText();
            Product prod = getProductByCode(Integer.parseInt(code));
            int price = getIntFromString(product.findElement(By.xpath(".//span[@class='price__current']")).getText());

            if (prod.getWarrantyList() != null) {
                String warranty = product.findElement(By.xpath(".//span[contains(@class, 'base-ui-radio-button__icon_checked')]")).getText();
                Assertions.assertTrue(formatString(warranty).contains("+12мес."), "Ошибка при выборе гарантии");
            }
            Assertions.assertEquals(prod.getPrice(), price, "Цена товара" + prod.getName() + " не совпадает");
        }
        Assertions.assertEquals(getSum(), getPrice(), "Общая стоимость не совпадает");
        return this;
    }

    //удаление товара в корзине по коду товара, осуществляется проверка уменьшения цены
    public CartPage deleteProductByCode(int codeProduct) {
        String startingPrice = totalPrice.getText();

        for (WebElement product : products) {
            int code = getIntFromString(product.findElement(By.xpath(".//div[@class='cart-items__product-code']/div")).getText());
            if (code == codeProduct) {
                waitUtilElementToBeClickable(product.findElement(By.xpath(".//button[@class='menu-control-button'][2]"))).click();
            }
        }
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(totalPrice, "innerText", startingPrice)));
        Assertions.assertEquals(getSum() - getProductByCode(codeProduct).getPrice(), getPrice(),
                "Общая стоимость корзины некорректна");
        return this;
    }

    //восстановить удаленный товар
    public CartPage restore() {
        String startingPrice = totalPrice.getText();
        waitUtilElementToBeClickable(restoreBtn.get(1)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(totalPrice, "innerText", startingPrice)));
        return this;
    }

    //проверка, что это страница "Корзина"
    public CartPage isCartPage() {
        Assertions.assertEquals("Корзина", title.getText(), "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    //метод, возвращающий цену корзины
    private int getPrice() {
        return getIntFromString(totalPrice.getText());
    }
}