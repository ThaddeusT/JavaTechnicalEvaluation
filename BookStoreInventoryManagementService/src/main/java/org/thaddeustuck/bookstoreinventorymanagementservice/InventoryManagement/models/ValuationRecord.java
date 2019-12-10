package org.thaddeustuck.bookstoreinventorymanagementservice.InventoryManagement.models;

public class ValuationRecord {
    private Integer upc;
    private Integer quantity;
    private Double price;
    private Double valuation;

    public ValuationRecord(Integer upc,Double price){
        this.upc = upc;
        this.price = price;
    }

    // Getter Methods

    public Integer getUpc() {
        return upc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getValuation(){
        return valuation;
    }

    // Setter Methods

    public void setUpc(Integer upc) {
        this.upc = upc;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setValuation(Double valuation){
        this.valuation = valuation;
    }
}
