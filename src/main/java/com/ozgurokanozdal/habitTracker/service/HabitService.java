package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.dto.HabitCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.HabitUpdateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserCreateRequest;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.LoggingPermission;
import java.util.stream.Collectors;


@Service
public class HabitService {


    private final HabitRepository habitRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository, ModelMapper modelMapper,UserRepository userRepository){
        this.habitRepository = habitRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    public List<HabitResponse> getAll(){

        return habitRepository.findAll().stream().map(element -> modelMapper.map(element, HabitResponse.class)).collect(Collectors.toList());
    }

    public HabitResponse getById(Long habitId){
        Habit habit  = habitRepository.findById(habitId).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(habit, HabitResponse.class);
    }

    public HabitCreateRequest create(HabitCreateRequest habitCreateRequest){
        User user = userRepository.findById(habitCreateRequest.getUserId()).orElseThrow(EntityNotFoundException::new);
        Habit habit = new Habit(habitCreateRequest.getName(),user);
        habitRepository.save(habit);
        return modelMapper.map(habit, HabitCreateRequest.class);
    }

    public HabitResponse update(Long habitId,HabitUpdateRequest habitUpdateRequest){
        Habit habit = habitRepository.findById(habitId).orElseThrow(EntityNotFoundException::new);

        habit.setName(habitUpdateRequest.getName());
        habitRepository.save(habit);
        return modelMapper.map(habit, HabitResponse.class);
    }


    public String delete(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(EntityNotFoundException::new);

        habitRepository.delete(habit);

        return "Habit -> " + habitId + " deleted";

    }
}
