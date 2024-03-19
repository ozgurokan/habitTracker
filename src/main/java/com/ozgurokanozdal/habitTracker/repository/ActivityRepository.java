package com.ozgurokanozdal.habitTracker.repository;

import com.ozgurokanozdal.habitTracker.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {

    Page<Activity> findAllByHabitId(long habitId, Pageable pageable);

    Page<Activity> findAllByUserId(long userId,Pageable pageable);
}
