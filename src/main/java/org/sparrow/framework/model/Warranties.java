package org.sparrow.framework.model;

public enum Warranties {
    MANUFACTURERS_12 ("Гарантия от производителя12 мес."),
    MANUFACTURERS_24 ("Гарантия от производителя24 мес."),
    ADDITIONAL_12 ("Доп. гарантия+ 12 мес."),
    ADDITIONAL_24 ("Доп. гарантия+ 24 мес.");

    private final String title;

    Warranties(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Warranties fromString(String text) {
        for (Warranties war : Warranties.values()) {
            if (war.title.equalsIgnoreCase(text)) {
                return war;
            }
        }
        return null;
    }
}
