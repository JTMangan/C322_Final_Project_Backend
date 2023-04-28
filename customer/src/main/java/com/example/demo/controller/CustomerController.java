package com.example.demo.controller;

import com.example.demo.handler.GlobalExceptionHandler;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "https://c322-final-project-frontend.vercel.app")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    // Get localhost:8080/customer
    @GetMapping
    public List<Customer> findAll() {
        List<Customer> customers = repository.findAll();
        List<Customer> responses = new ArrayList<>();

        for (Customer customer : customers) {
            Customer response = new Customer();
            response.setTableId(customer.getTableId());
            response.setName(customer.getName());
            response.setNumberOfPeople(customer.getNumberOfPeople());
            response.setKids(customer.getKids());
            response.setNumberOfKids(customer.getNumberOfKids());
            responses.add(response);
        }

        return responses;
    }

    // POST localhost:8080/customer , @RequestParam int numberOfPeople, @RequestParam int numberOfKids

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@Valid @RequestBody Customer request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setNumberOfPeople(request.getNumberOfPeople());
        customer.setKids(request.getKids());
        customer.setNumberOfKids(request.getNumberOfKids());
        Customer newCustomer = repository.save(customer);
        return newCustomer.getTableId();
    }
    // PUT localhost:8080/customer/2
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Customer customer, @PathVariable int id){
        customer.setNumberOfKids(id);
        repository.save(customer);
    }

    // DELETE localhost:8080/customer/2
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}