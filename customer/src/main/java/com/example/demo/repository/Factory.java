package com.example.demo.repository;

import com.example.demo.model.Customer;

public interface Factory {
    int create(Customer customer);
}