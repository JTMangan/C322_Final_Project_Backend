package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "appetizers")
    private String appetizers;

    @Column(name = "main_dishes")
    private String mainDishes;

    @Column(name = "dessert")
    private String dessert;

    @Column(name = "beverages")
    private String beverages;

    private int quantity;

    public OrderItem() {
    }

    public OrderItem(String appetizers) {
        this.appetizers = appetizers;
    }

    public OrderItem(int id, String appetizers, String mainDishes, String dessert, String beverages, int quantity) {
        this.id = id;
        this.appetizers = appetizers;
        this.mainDishes = mainDishes;
        this.dessert = dessert;
        this.beverages = beverages;
        this.quantity = quantity;
    }

    public OrderItem(String appetizers, String mainDishes, String dessert, String beverages) {

        this.appetizers = appetizers;
        this.mainDishes = mainDishes;
        this.dessert = dessert;
        this.beverages = beverages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppetizers() {
        return appetizers;
    }

    public void setAppetizers(String appetizers) {
        this.appetizers = appetizers;
    }

    public String getMainDishes() {
        return mainDishes;
    }

    public void setMainDishes(String mainDishes) {
        this.mainDishes = mainDishes;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getBeverages() {
        return beverages;
    }

    public void setBeverages(String beverages) {
        this.beverages = beverages;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
