package c322Project.iu.edu.invoice.Controller;


import c322Project.iu.edu.invoice.Model.Invoice;
import c322Project.iu.edu.invoice.Model.OrderItem;
import c322Project.iu.edu.invoice.Repository.InvoiceRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://c322-final-project-frontend.vercel.app")
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    private List<Invoice> invoices = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Invoice>> getOrders() {
        if (!invoices.isEmpty()) {
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> request) {
        int tableId = (int) request.get("tableId");
        double total = (double) request.get("total");
        List<Map<String, Object>> items = (List<Map<String, Object>>) request.get("items");

        List<OrderItem> orderItems = new ArrayList<>();
        for (Map<String, Object> item : items) {
            int itemId = (int) item.get("ItemId");
            String appetizers = (String) item.get("Appetizers");
            String mainDishes = (String) item.get("MainDishes");
            String dessert = (String) item.get("Dessert");
            String beverages = (String) item.get("Beverages");
            OrderItem orderItem = new OrderItem(itemId, appetizers, mainDishes, dessert, beverages,0);
            orderItems.add(orderItem);
        }

        Invoice invoice = new Invoice(tableId, orderItems, total);
        invoices.add(invoice);

        return new ResponseEntity<>("Order created successfully.", HttpStatus.CREATED);
    }

}
