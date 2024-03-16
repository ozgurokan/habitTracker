package com.ozgurokanozdal.habitTracker.repository;

import com.ozgurokanozdal.habitTracker.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {
}
