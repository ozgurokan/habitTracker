package com.ozgurokanozdal.habitTracker.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    private User user;

    private Set<Activity> activities;


}
