package com.ozgurokanozdal.habitTracker.dto;


public class HabitUpdateRequest {

    private String name;

    public HabitUpdateRequest(String name) {
        this.name = name;
    }

    public HabitUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
