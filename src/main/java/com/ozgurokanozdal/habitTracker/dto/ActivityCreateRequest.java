package com.ozgurokanozdal.habitTracker.dto;

public class ActivityCreateRequest {


    private String name;

    private Long habit_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHabit_id() {
        return habit_id;
    }

    public void setHabit_id(Long habit_id) {
        this.habit_id = habit_id;
    }
}
