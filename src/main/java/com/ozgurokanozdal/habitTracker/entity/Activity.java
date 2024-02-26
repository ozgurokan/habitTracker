package com.ozgurokanozdal.habitTracker.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;


@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Habit habit;


    private Instant createTime;


    public Activity(String name, Habit habit, Instant createTime) {
        this.name = name;
        this.habit = habit;
        this.createTime = createTime;
    }

    public Activity() {
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
}
