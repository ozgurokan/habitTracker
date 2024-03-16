package com.ozgurokanozdal.habitTracker.dto;

public class ActivityCreateRequest {


    private String name;

    private Long habit_id;

    private Long user_id;


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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
