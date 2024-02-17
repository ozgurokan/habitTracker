package com.ozgurokanozdal.habitTracker.entity;


import jakarta.persistence.*;

import java.util.Set;


@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String name;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Habit> habitSet;



}
