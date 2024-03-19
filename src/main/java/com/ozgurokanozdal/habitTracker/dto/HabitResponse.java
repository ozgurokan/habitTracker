package com.ozgurokanozdal.habitTracker.dto;



import java.util.List;

public class HabitResponse {

    private long id;

    private String name;

    private UserResponse user;

    private List<LikeListResponse> likesList;


    public HabitResponse(long id, String name,UserResponse user,List<LikeListResponse> likesList) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.likesList =likesList;
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


    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public List<LikeListResponse> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<LikeListResponse> likesList) {
        this.likesList = likesList;
    }
}
