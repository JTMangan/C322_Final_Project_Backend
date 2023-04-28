package c322Project.iu.edu.invoice.Model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableId;

    public Invoice(int tableId, List<OrderItem> items, double total) {
        this.tableId = tableId;
        this.items = items;
        this.total = total;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;
    private double total;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(Long orderId) {
        this.tableId = tableId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem>  items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}




