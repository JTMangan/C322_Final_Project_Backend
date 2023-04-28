package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "https://c322-final-project-frontend.vercel.app")
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private List<Order> orders = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> request) {
        int tableId = (int) request.get("tableId");
        List<Map<String, Object>> items = (List<Map<String, Object>>) request.get("items");

        List<OrderItem> orderItems = new ArrayList<>();
        for (Map<String, Object> item : items) {
            int itemId = (int) item.get("ItemId");
            String appetizers = (String) item.get("Appetizers");
            String mainDishes = (String) item.get("MainDishes");
            String dessert = (String) item.get("Dessert");
            String beverages = (String) item.get("Beverages");
            int quantity = (int) item.get("quantity");
            OrderItem orderItem = new OrderItem(itemId, appetizers, mainDishes, dessert, beverages, quantity);
            orderItems.add(orderItem);
        }

        Order order = new Order(tableId, orderItems);
        orders.add(order);

        return new ResponseEntity<>("Order created successfully.", HttpStatus.CREATED);
    }

    @PostMapping("/validateTable")
    public ResponseEntity<String> validateTable(@RequestBody Map<String, Object> request) {
        int tableId = (int) request.get("tableId");
        for (Order order : orders) {
            if (order.getTableId() == tableId) {
                return new ResponseEntity<>("Table is valid.", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Invalid table ID.", HttpStatus.BAD_REQUEST);
    }

//    @PutMapping("/order/{id}")
//    public ResponseEntity<String> updateOrder(@RequestBody Map<String, Object> request, @PathVariable int id) {
//        String appetizers = (String) request.get("appetizers");
//        String mainDishes = (String) request.get("mainDishes");
//        String dessert = (String) request.get("dessert");
//        String beverages = (String) request.get("beverages");
//        String reason = (String) request.get("reason");
//
//        Optional<Order> optionalOrder = orders.stream().filter(order -> order.getTableId() == id).findFirst();
//
//        if (optionalOrder.isPresent()) {
//            Order order = optionalOrder.get();
//            boolean itemCancelled = false;
//            for (OrderItem item : order.getItems()) {
//                if (item.getAppetizers().equals(appetizers)) {
//                    item.setAppetizers("");
//                    itemCancelled = true;
//                } else if (item.getMainDishes().equals(mainDishes)) {
//                    item.setMainDishes("");
//                    itemCancelled = true;
//                } else if (item.getDessert().equals(dessert)) {
//                    item.setDessert("");
//                    itemCancelled = true;
//                } else if (item.getBeverages().equals(beverages)) {
//                    item.setBeverages("");
//                    itemCancelled = true;
//                }
//            }
//            if (itemCancelled) {
//                return new ResponseEntity<>("Order item cancelled successfully.", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Order item not found.", HttpStatus.NOT_FOUND);
//            }
//        } else {
//            return new ResponseEntity<>("Order not found.", HttpStatus.NOT_FOUND);
//        }
//    }


    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getTableId() == orderId) {
                orders.remove(i);
                return new ResponseEntity<>("Order deleted successfully.", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Order not found.", HttpStatus.NOT_FOUND);
    }












    @PutMapping("/order/{id}")
    public ResponseEntity<String> updateOrder(@RequestBody Map<String, Object> request, @PathVariable int id) {
        String appetizers = (String) request.get("appetizers");
        String mainDishes = (String) request.get("mainDishes");
        String dessert = (String) request.get("dessert");
        String beverages = (String) request.get("beverages");
        String reason = (String) request.get("reason");
        int itemId = (int) request.get("id");

        Optional<Order> optionalOrder = orders.stream().filter(order -> order.getTableId() == id).findFirst();

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            boolean itemCancelled = false;
            for (OrderItem item : order.getItems()) {
                if (item.getId() == itemId) {
                    if (appetizers != null && !appetizers.isEmpty()) {
                        item.setAppetizers(appetizers);
                    }
                    if (mainDishes != null && !mainDishes.isEmpty()) {
                        item.setMainDishes(mainDishes);
                    }
                    if (dessert != null && !dessert.isEmpty()) {
                        item.setDessert(dessert);
                    }
                    if (beverages != null && !beverages.isEmpty()) {
                        item.setBeverages(beverages);
                    }
                    return new ResponseEntity<>("Order item updated successfully.", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Order item not found.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Order not found.", HttpStatus.NOT_FOUND);
        }
    }










}








