package com.ozgurokanozdal.habitTracker.dto;

import com.ozgurokanozdal.habitTracker.entity.User;

public class HabitCreateRequest {

    private String name;

    private Long userId;

    public HabitCreateRequest(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }

    public HabitCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
