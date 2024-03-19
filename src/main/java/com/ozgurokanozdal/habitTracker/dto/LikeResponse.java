package com.ozgurokanozdal.habitTracker.dto;

public class LikeResponse{

    private Long id;
    private Long habitId;
    private Long userId;

    public LikeResponse() {
    }

    public LikeResponse(Long id, Long habitId, Long userId) {
        this.id = id;
        this.habitId = habitId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
