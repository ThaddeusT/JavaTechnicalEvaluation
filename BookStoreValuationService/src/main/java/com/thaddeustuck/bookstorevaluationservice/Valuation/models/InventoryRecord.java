package com.thaddeustuck.bookstorevaluationservice.Valuation.models;

public class InventoryRecord {
    private Integer upc;
    private Integer quantity;
    private Integer version;

    // Getter Methods
    public Integer getUpc() {
        return upc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getVersion() {
        return version;
    }

    // Setter Methods
    public void setUpc(Integer upc) {
        this.upc = upc;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
