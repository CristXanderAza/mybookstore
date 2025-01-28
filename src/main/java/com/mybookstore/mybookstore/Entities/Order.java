package com.mybookstore.mybookstore.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    private Boolean sended;

    @ManyToOne
    @JoinColumn(name = "OrderCustomer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "OrderCashier_id", nullable = true)
    private Cashier sendedBy;

    public Order(){}

    public Order(Customer customer){
        this.items = new ArrayList<>();
        this.customer = customer;
        this.sended = false;
    }

    public void addItem(Book b, int cantity){
        b.reduceExistence(cantity);
        OrderItem item = new OrderItem(b, cantity);
        items.add(item);
        total += item.getSubtotal();
    }

    public void Send(){
        this.sended = true;
    }
}
