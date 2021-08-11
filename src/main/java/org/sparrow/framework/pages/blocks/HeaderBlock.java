package org.sparrow.framework.pages.blocks;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.sparrow.framework.pages.*;

public class HeaderBlock extends BasePage {

    @FindBy(xpath = "//nav[@id='header-search']//input[@type='search']")
    private WebElement searchBar;

    @FindBy(xpath = "//nav[@id='header-search']//a[@class='ui-link cart-link']")
    private WebElement cart;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement cartPriceSum;

    //TODO параметризировать
    //отправляем поисковый запрос
    public ProductPage sendSearchRequestToProductPage(String requestText) {
        sendSearchRequest(requestText);
        return pageManager.getProductPage().isProductPage();
    }

    public ProductsListPage sendSearchRequestToProductsListPage(String requestText) {
        sendSearchRequest(requestText);
        return pageManager.getProductsListPage().isProductsListPageByXpath().isProductsListPageByUrl();
    }

    public ProductsCategoryPage sendSearchRequestToProductsCategoryPage(String requestText) {
        sendSearchRequest(requestText);
        return pageManager.getProductsCategoryPage();
    }

    //вынесен дублирующий код
    private void sendSearchRequest(String requestText) {
        inputTextIntoSearchBar(requestText);
        searchBar.sendKeys(Keys.ENTER);
    }

    //нет в дз
    //вести текст в строку поиска, отдельный метод, т.к. можем выполнять проверку рекомендуемого списка для поиска (до отправки запроса)
    public HeaderBlock inputTextIntoSearchBar(String requestText) {
        fillInputField(searchBar, requestText);
        return this;
    }

    public CartPage clickCartHeader() {
        waitUtilElementToBeClickable(cart).click();
        return pageManager.getCartPage().isCartPage();
    }

    public HeaderBlock checkSum() {
        Assertions.assertEquals(getSum(), getIntFromString(cartPriceSum.getText()), "Сумма покупок не совпадает");
        return this;
    }

    public HeaderBlock toHeaderBlock() {
        return this;
    }
}