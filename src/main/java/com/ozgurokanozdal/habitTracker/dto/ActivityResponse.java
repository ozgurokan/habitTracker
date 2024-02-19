package com.ozgurokanozdal.habitTracker.dto;


import com.ozgurokanozdal.habitTracker.entity.Habit;

import java.time.Instant;

public class ActivityResponse {

    private long id;

    private String name;

    private Long habitId;

    private Instant createTime;

    public ActivityResponse(long id, String name, Long habitId, Instant createTime) {
        this.id = id;
        this.name = name;
        this.habitId = habitId;
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

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }
}
