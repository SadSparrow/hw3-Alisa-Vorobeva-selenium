package org.sparrow.framework.managers;

import org.sparrow.framework.pages.*;
import org.sparrow.framework.pages.blocks.HeaderBlock;

public class PageManager {

    private static PageManager pageManager;
    private HeaderBlock headerBlock;
    private ProductsListPage productsListPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private ProductsCategoryPage productsCategoryPage;

    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public HeaderBlock getHeaderBlock() {
        if (headerBlock == null) {
            headerBlock = new HeaderBlock();
        }
        return headerBlock;
    }

    public ProductsListPage getProductsListPage() {
        if (productsListPage == null) {
            productsListPage = new ProductsListPage();
        }
        return productsListPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }
    public ProductsCategoryPage getProductsCategoryPage() {
        if (productsCategoryPage == null) {
            productsCategoryPage = new ProductsCategoryPage();
        }
        return productsCategoryPage;
    }
}
