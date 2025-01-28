package com.mybookstore.mybookstore.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
@Getter 
@Setter
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String firsName;
    protected String lastName;
    protected String userName;
    protected String password;

    public User(){}

    public User(String firsName, String lastName, String userName, String password) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
    
}
