package com.ozgurokanozdal.habitTracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class Habit {

    @Id
    @GeneratedValue
    private long id;


    @Column
    private String name;


    private User user;

    private Set<Activity> activities;


}
