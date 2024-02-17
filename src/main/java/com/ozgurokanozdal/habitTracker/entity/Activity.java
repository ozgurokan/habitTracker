package com.ozgurokanozdal.habitTracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;


@Entity
public class Activity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private Habit habit;

    @Column
    private Instant createTime;

    @Column
    private Instant updateTime;


}
