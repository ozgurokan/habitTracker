package com.ozgurokanozdal.habitTracker.dto;

import java.time.Instant;

public class HabitUpdateRequest {

    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}