package com.ozgurokanozdal.habitTracker.entity;

import jakarta.persistence.*;

import java.time.Instant;


@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Habit habit;

    private Instant createTime;

    private Instant updateTime;


}
