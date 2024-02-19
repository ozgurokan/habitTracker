package com.ozgurokanozdal.habitTracker.repository;

import com.ozgurokanozdal.habitTracker.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit,Long> {


    List<Habit> findAllByUserId(long userId);
}
