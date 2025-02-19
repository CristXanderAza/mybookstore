package com.mybookstore.mybookstore.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer extends User {

    private String address;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Order> orders; 

    public Customer(String firsName, String lastName, String userName, String password, String address) {
        super(firsName, lastName, userName, password);
        this.address = address;
    }
        
} 
