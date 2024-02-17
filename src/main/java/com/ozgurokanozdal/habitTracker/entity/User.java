package com.ozgurokanozdal.habitTracker.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {


    @Id
    @GeneratedValue
    private long id;


    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String email;



}
