package com.mybookstore.mybookstore.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OrderBook_id", nullable = false)
    private Book book;
    private int cantity;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "Order_id", nullable = false)
    private Order order;

    public OrderItem(Book book, int cantity) {
        this.book = book;
        this.cantity = cantity;
        this.subtotal = book.getPrice() * cantity;
    }  

    public Boolean modifyCantity(int nCantity){
        if(book.getExistence() >= nCantity){
            this.cantity = nCantity;
            return true;
        }
        return false;
    }

    
}
