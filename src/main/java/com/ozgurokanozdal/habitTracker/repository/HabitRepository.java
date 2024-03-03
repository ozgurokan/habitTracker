package com.ozgurokanozdal.habitTracker.repository;

import com.ozgurokanozdal.habitTracker.entity.Habit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit,Long> {


    List<Habit> findAllByUserId(long userId);

}
