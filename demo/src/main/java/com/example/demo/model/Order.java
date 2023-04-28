package com.example.demo.model;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Column(name = "table_id")
    private int tableId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public Order() {
    }

    public Order(int tableId, List<OrderItem> items) {
        this.tableId = tableId;
        this.items = items;
    }



    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public void addItem(OrderItem item) {
        items.add(item);
    }
    public OrderItem getItemById(int itemId) {
        for (OrderItem item : items) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }



    public boolean removeItem(OrderItem itemToRemove) {
        items.remove(itemToRemove);
        return true;
    }

}