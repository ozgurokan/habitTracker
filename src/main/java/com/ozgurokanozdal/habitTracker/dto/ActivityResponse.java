package com.ozgurokanozdal.habitTracker.dto;


import com.ozgurokanozdal.habitTracker.entity.Habit;

import java.time.Instant;

public class ActivityResponse {

    private long id;

    private String name;

    private Habit habit;

    private Instant createTime;

    public ActivityResponse(long id, String name, Habit habit, Instant createTime) {
        this.id = id;
        this.name = name;
        this.habit = habit;
        this.createTime = createTime;
    }

    public ActivityResponse() {
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

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }
}
