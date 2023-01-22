package org.example.model;

public enum OrderType {
    BIDS("bids"),
    ASKS("asks");

    private String name;

    OrderType(String name) {
        this.name = name;
    }

    public String geOrderType() {
        return name;
    }

}
