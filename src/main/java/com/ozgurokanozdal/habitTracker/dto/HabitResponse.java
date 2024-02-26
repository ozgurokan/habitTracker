package com.ozgurokanozdal.habitTracker.dto;


public class HabitResponse {

    private long id;

    private String name;

    private long userId;

    public HabitResponse(long id, String name, long userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public HabitResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
