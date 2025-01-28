package com.mybookstore.mybookstore.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cashier extends User{

    private double salary;

    @OneToMany(mappedBy = "sendedBy", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Order> orderProseced;

    public Cashier(String firsName, String lastName, String userName, String password, double salary) {
        super(firsName, lastName, userName, password);
        this.salary = salary;
    }

    
}
