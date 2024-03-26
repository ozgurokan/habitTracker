package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.dto.*;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.Likes;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.exceptions.ContentNotFoundException;
import com.ozgurokanozdal.habitTracker.exceptions.UserNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.repository.LikeRepository;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class HabitService {


    private final HabitRepository habitRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ActivityService activityService;
    private final LikeRepository likeRepository;

    public HabitService(HabitRepository habitRepository,
                        ModelMapper modelMapper,UserRepository userRepository,
                        ActivityService activityService,LikeRepository likeRepository){
        this.habitRepository = habitRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.activityService = activityService;
        this.likeRepository = likeRepository;
    }


    public List<HabitResponse> getAll(){
        return habitRepository.findAll().stream().map(element -> modelMapper.map(element, HabitResponse.class)).collect(Collectors.toList());
    }


    // deneme
    public Page<HabitResponse> getAllWithPage(Pageable pageable){
        return habitRepository.findAll(pageable).map(e -> modelMapper.map(e, HabitResponse.class));
    }

    public HabitResponse getById(Long habitId){
        Habit habit  = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        return modelMapper.map(habit, HabitResponse.class);
    }

    public HabitResponse create(HabitCreateRequest habitCreateRequest){
        User user = userRepository.findById(habitCreateRequest.getUserId()).orElseThrow(UserNotFoundException::new);
        Habit habit1 = habitRepository.save(new Habit(habitCreateRequest.getName(),user));
        return modelMapper.map(habit1, HabitResponse.class);
    }

    public HabitResponse update(Long habitId,HabitUpdateRequest habitUpdateRequest){
        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        habit.setName(habitUpdateRequest.getName());
        habitRepository.save(habit);
        return modelMapper.map(habit, HabitResponse.class);
    }


    public String delete(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        habitRepository.delete(habit);
        return "Habit -> " + habitId + " deleted";

    }

    public List<ActivityResponse> getHabitActivityList(Long habitId){
        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        return habit.getActivities().stream().map(activity -> modelMapper.map(activity,ActivityResponse.class)).toList();
    }


    //this method returns activity list of a habit with pagination. maybe this should be in activity service?
    public Page<ActivityResponse> getHabitActivityListWithPage(long habitId, Pageable pageable) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        return activityService.getAllByHabitId(habitId,pageable);
    }


    //like response DTO will add
    public Page<LikeResponse> getHabitLikesListWithPage(long habitId, Pageable pageable) {

        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        return likeRepository.findAllByHabitId(habit.getId(),pageable).map(likes -> modelMapper.map(likes, LikeResponse.class));
    }
}
