package com.ozgurokanozdal.habitTracker.dto;

import com.ozgurokanozdal.habitTracker.entity.Habit;

import java.util.List;

public class UserResponse {

    private long id;

    private String username;


    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
