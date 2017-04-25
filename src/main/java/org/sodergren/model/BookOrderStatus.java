package org.sodergren.model;

public enum BookOrderStatus {
    OK(0),
    NOT_IN_STOCK(1),
    DOES_NOT_EXIST(2);

    private final int statusCode;

    BookOrderStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public static BookOrderStatus get(int i) {
        for (BookOrderStatus value : BookOrderStatus.values()) {
            if (value.getStatusCode() == i) {
                return value;
            }
        }

        throw new IllegalArgumentException("Could not find status for code: " + i);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
