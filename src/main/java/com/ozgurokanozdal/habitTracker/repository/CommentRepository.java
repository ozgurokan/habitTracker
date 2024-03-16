package com.ozgurokanozdal.habitTracker.repository;

import com.ozgurokanozdal.habitTracker.dto.CommentResponse;
import com.ozgurokanozdal.habitTracker.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {


    Page<Comment> findAllByHabitId(Long commentId, Pageable pageable);

    Page<Comment> findAllByUserId(Long userId, Pageable pageable);

}
