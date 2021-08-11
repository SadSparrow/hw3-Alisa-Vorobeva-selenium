package org.sparrow.framework.managers;

import org.sparrow.framework.model.Product;
import org.sparrow.framework.model.Warranties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataManager {

    private static DataManager INSTANCE = null;
    private List<Product> expectedProducts;
    private List<Product> resultProducts;


    private DataManager() {
    }

    public static DataManager getDataManager() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public List<Product> getData() {
        if (expectedProducts == null) {
            initList();
        }
        return expectedProducts;
    }

    public List<Product> setData() {
        if (resultProducts == null) {
            resultProducts = new ArrayList<>();
        }
        return resultProducts;
    }

    private void initList() {
        expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product("Кофемашина Philips EP2035/40 черный", 1357630, 36999,
                new ArrayList<>(Arrays.asList(
                        new Product.Warranty(Warranties.MANUFACTURERS_24, 0),
                        new Product.Warranty(Warranties.ADDITIONAL_12, 4810),
                        new Product.Warranty(Warranties.ADDITIONAL_24, 6660)
                ))));

        expectedProducts.add(new Product("Универсальный цифровой ключ Hideez Key", 1146474, 1250));
    }
}
