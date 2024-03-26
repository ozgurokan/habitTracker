package com.ozgurokanozdal.habitTracker.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "habit",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Activity> activities;

    @OneToMany(mappedBy = "habit",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Likes> likesList;

    public Habit() {
    }

    public Habit(String name, User user) {
        this.name = name;
        this.user = user;
    }
    public Habit(long id,String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Activity> getActivities(){
        return activities;
    }

    public List<Likes> getLikesList() {
        return likesList;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
