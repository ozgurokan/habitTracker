package com.ozgurokanozdal.habitTracker.dto;


import java.time.LocalDateTime;

public class CommentResponse{


    private Long id;
    private String commentText;
    private UserResponse user;
    private Long habitId;
    private LocalDateTime createDate;

    public CommentResponse() {
    }

    public CommentResponse(Long id, String commentText, UserResponse user, Long habitId, LocalDateTime createDate) {
        this.id = id;
        this.commentText = commentText;
        this.user = user;
        this.habitId = habitId;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
