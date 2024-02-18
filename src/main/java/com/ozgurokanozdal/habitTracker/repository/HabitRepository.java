package com.ozgurokanozdal.habitTracker.repository;

import com.ozgurokanozdal.habitTracker.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit,Long> {
    
}
