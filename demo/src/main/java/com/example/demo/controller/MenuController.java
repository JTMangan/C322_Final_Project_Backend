package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @GetMapping
    public String menu(){
        return "----------------------------------------"
                + "\n ~~~~~~~ WELCOME TO LUDDY CAFFE ~~~~~~~"
                + "\n----------------------------------------"
                + "\n ~~~~~~~~~~~~~~~~ Menu ~~~~~~~~~~~~~~~~"
                + "\n-- Appetizers: -------------------------"
                + "\n------- Ahi Poke Nachos ----------------"
                + "\n------- Coconut Shrimp -----------------"
                + "\n----------------------------------------"
                + "\n-- Main Dishes: ------------------------"
                + "\n------- Dry-Aged Bone-In Ribeye (22 oz)-"
                + "\n------- Delmonico Ribeye (16 oz)* ------"
                + "\n------- Wagyu Filet --------------------"
                + "\n------- Lamb Lollipops -----------------"
                + "\n----------------------------------------"
                + "\n-- Dessert: ----------------------------"
                + "\n------- Molten Chocolate Cake ----------"
                + "\n------- Red Velvet Cake Cheesecake -----"
                + "\n----------------------------------------"
                + "\n-- Beverages: --------------------------"
                + "\n------- Coke®, Diet Coke®, Sprite®, ----"
                + "\n------- The \"Jewels\", The Vesper -----"
                + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
