package org.sparrow.tests;

import org.junit.jupiter.api.Test;
import org.sparrow.tests.base.BaseTests;

public class DnsShopTest extends BaseTests {


    @Test
    public void startTest() {
        app.getHeaderBlock()
                .sendSearchRequestToProductsListPage("кофемашина philips")
                .clickFirstProduct()
                .memorizeProduct()
                .selectTabFromAdditionalSalesTabsList("гарантия")
                .selectWarrantyRadioBtn("гарантия+ 12")
                .clickBuyProductBtn()
                .toHeaderBlock()
                .sendSearchRequestToProductPage("менеджер паролей")
                .memorizeProduct()
                .clickBuyProductBtn()
                .toHeaderBlock()
                .checkSum()
                .clickCartHeader()
                .checkTotal()
                .deleteProductByCode(1146474)
                .increaseQuantity(2, 1357630)
                .restore();
    }
}
