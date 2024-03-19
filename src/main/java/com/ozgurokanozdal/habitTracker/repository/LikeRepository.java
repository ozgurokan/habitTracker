package com.ozgurokanozdal.habitTracker.repository;

import com.ozgurokanozdal.habitTracker.entity.Likes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {

    Page<Likes> findAllByHabitId(long habitId, Pageable pageable);

    Page<Likes> findAllByUserId(long userId,Pageable pageable);

    Optional<Likes> findByUserIdAndHabitId(long userId, long habitId);
}
