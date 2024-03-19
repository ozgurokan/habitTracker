package com.ozgurokanozdal.habitTracker.dto;

public class LikeListResponse{

    private long id;
    private long userId;


    public LikeListResponse(long id,long userId){
        this.id = id;
        this.userId=userId;
    }

    public LikeListResponse(){

    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
