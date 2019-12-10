package org.thaddeustuck.bookstoreinventorymanagementservice.InventoryManagement.models;

public class Book {
    private Integer upc;
    private String title;
    private String author;
    private String description;
    private Double price;

    public Book(Integer upc, String title, String author, String description, Double price){
        this.upc = upc;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    // Getter Methods

    public Integer getUpc() {
        return upc;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    // Setter Methods

    public void setUpc(Integer upc) {
        this.upc = upc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
