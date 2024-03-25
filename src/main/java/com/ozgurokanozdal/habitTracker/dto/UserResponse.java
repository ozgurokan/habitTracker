package com.ozgurokanozdal.habitTracker.dto;



public class UserResponse {

    private long id;

    private String username;

    private String name;

    public UserResponse(long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
