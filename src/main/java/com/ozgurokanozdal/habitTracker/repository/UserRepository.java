package com.ozgurokanozdal.habitTracker.repository;

import com.ozgurokanozdal.habitTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}
