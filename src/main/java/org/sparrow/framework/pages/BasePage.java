package org.sparrow.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sparrow.framework.managers.DataManager;
import org.sparrow.framework.managers.DriverManager;
import org.sparrow.framework.managers.PageManager;
import org.sparrow.framework.managers.TestPropManager;
import org.sparrow.framework.model.Product;

import java.util.List;
import java.util.Locale;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getDriverManager();
    protected PageManager pageManager = PageManager.getPageManager();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    protected DataManager dataManager = DataManager.getDataManager();
    private final TestPropManager props = TestPropManager.getTestPropManager();
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();


    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    //явное ожидание того, что элемент станет видимым
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //явное ожидание того, что элемент станет кликабельным
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //прокрутка страницы вверх
    protected void scrollToTopJs() {
        js.executeScript("scroll(0, -250);");
    }

    //заполнение inputа с проверкой
    protected void fillInputField(WebElement field, String value) {
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
        try {
            wait.until(ExpectedConditions.attributeContains(field, "value", value));
        } catch (TimeoutException e) {
            Assertions.fail("Поле было заполнено некорректно");
        }
    }

    //форматирование строки: удаление пробелов и перевод в нижний регистр
    protected String formatString(String string) {
        return string.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
    }

    //форматирование строки в число
    protected int getIntFromString(String string) {
        return Integer.parseInt(formatString(string).replaceAll("[^\\d.]", ""));
    }

    //методы для "запомианания" товара и цен==================================
    //TODO задумано иначе
    protected List<Product> getExpectedProductsList() {
        return dataManager.getData();
    }

    protected List<Product> getResultProductsList() {
        return dataManager.setData();
    }

    protected void setProduct(Product product) {
        getResultProductsList().add(product);
    }

    protected Product getProductByCode(int code) {
        for (Product product : getResultProductsList()) {
            if (product.getCode() == code) {
                return product;
            }
        }
        return null;
    }

    protected int getSum() {
        int price = 0;
        for (Product product : getResultProductsList()) {
            if (product.getWarrantyList() != null) {
                for (Product.Warranty warranty : product.getWarrantyList()) {
                    price += warranty.getPrice();
                }
            }
            price += product.getPrice();
        }
        return price;
    }
    //===========================================================================
}