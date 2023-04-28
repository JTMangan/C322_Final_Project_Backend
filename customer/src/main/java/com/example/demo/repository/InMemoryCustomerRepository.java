package com.example.demo.repository;


import com.example.demo.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomerRepository implements Factory{
    private List<Customer> customers = new ArrayList<>();
    public List<Customer> findAll(){
        List<Customer> customersWithDetails = new ArrayList<>();

        for(Customer customer : customers){
            Customer customerWithDetails = new Customer();
            customerWithDetails.setTableId(customer.getTableId());
            customerWithDetails.setName(customer.getName());
            customerWithDetails.setNumberOfPeople(customer.getNumberOfPeople());
            customerWithDetails.setKids(customer.getKids());
            customerWithDetails.setNumberOfKids(customer.getNumberOfKids());

            // add number of people and kids to the response
            String details = customer.getKids() + " people";
            if(customer.getKids().equalsIgnoreCase("yes")){
                details += ", " + customer.getKids() + " kids";
            }
            customersWithDetails.add(customerWithDetails);
        }

        return customersWithDetails;
    }
    @Override
    public int create(Customer customer){
        int id = customers.size();
        int people = customer.getNumberOfPeople();
        int kids = customer.getNumberOfKids();
        customer.setTableId(id);
        customer.setNumberOfPeople(people);
        customer.setNumberOfKids(kids);
        customers.add(customer);
        return id;
    }

    public void update(Customer customer, int id){
        Customer customerById = getCustomerById(id);
        if(customerById != null){
            customerById.setTableId(customer.getTableId());
            customerById.setNumberOfPeople(customer.getNumberOfPeople());
            customerById.setKids(customer.getKids());
            customerById.setNumberOfKids(customer.getNumberOfKids());
        } else {
            throw new IllegalStateException("Invalid date");
        }
    }

    public void delete(int id){
        Customer x = getCustomerById(id);
        if(x!=null){
            customers.remove(x);
        } else {
            throw new IllegalStateException("table id is not valid");
        }
    }

    private Customer getCustomerById(int id) {
        return customers.stream().filter(x -> x.getTableId() == id).findAny().orElse(null);
    }
}
