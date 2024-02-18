package com.ozgurokanozdal.habitTracker.entity;


import jakarta.persistence.*;

import java.util.Set;


@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    private String name;

    private String username;

    private String password;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Habit> habitSet;


    public User(){

    }
    public User(String name, String username, String password,String email){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;

    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Set<Habit> getHabitSet() {
        return habitSet;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
