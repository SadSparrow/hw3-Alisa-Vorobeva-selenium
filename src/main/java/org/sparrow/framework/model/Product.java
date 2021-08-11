package org.sparrow.framework.model;

import java.util.List;
import java.util.Objects;

public class Product {
    private String name;
    private int code;
    private int price;
    private List<Warranty> warrantyList;

    public Product(String name, int code, int price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public Product(String name, int code, int price, List<Warranty> warrantyList) {
        this(name, code, price);
        this.warrantyList = warrantyList;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public List<Warranty> getWarrantyList() {
        return warrantyList;
    }

    public void setWarrantyList(List<Warranty> warrantyList) {
        this.warrantyList = warrantyList;
    }

    public static class Warranty {
        Warranties name;
        int price;

        public Warranty(Warranties name, int price) {
            this.name = name;
            this.price = price;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Warranty warranty = (Warranty) o;

            if (price != warranty.price) return false;
            return Objects.equals(name, warranty.name);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + price;
            return result;
        }

        @Override
        public String toString() {
            return "Warranty{" +
                    "name=" + name +
                    ", price=" + price +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return code == product.code;
    }

    @Override
    public int hashCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", price=" + price +
                ", warrantyList=" + warrantyList +
                '}';
    }
}
