package com.ozgurokanozdal.habitTracker.service;

import com.ozgurokanozdal.habitTracker.dto.LikeCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.LikeResponse;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.Likes;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.exceptions.AlreadyExistException;
import com.ozgurokanozdal.habitTracker.exceptions.ContentNotFoundException;
import com.ozgurokanozdal.habitTracker.exceptions.UserNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.repository.LikeRepository;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    public LikeService(LikeRepository likeRepository,ModelMapper modelMapper,UserRepository userRepository,HabitRepository habitRepository){
        this.likeRepository = likeRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
    }



    public List<LikeResponse> getAll(){
        return likeRepository.findAll().stream().map(likes -> modelMapper.map(likes, LikeResponse.class)).collect(Collectors.toList());
    }


    public LikeResponse create(LikeCreateRequest likeCreateRequest){
        User user = userRepository.findById(likeCreateRequest.userId()).orElseThrow(UserNotFoundException::new);
        Habit habit = habitRepository.findById(likeCreateRequest.habitId()).orElseThrow(ContentNotFoundException::new);

        if(likeRepository.findByUserIdAndHabitId(likeCreateRequest.userId(), likeCreateRequest.habitId()).isEmpty()){
            return modelMapper.map(likeRepository.save(new Likes(habit,user)), LikeResponse.class);
        }else{
            throw new AlreadyExistException("Like already exists");
        }
    }

    public List<LikeResponse> getAllByUserId(long userId, Pageable pageable) {
        if(userRepository.existsById(userId)){
            return likeRepository.findAllByUserId(userId,pageable).stream().map(likes -> modelMapper.map(likes, LikeResponse.class)).collect(Collectors.toList());
        }else{
            throw new UserNotFoundException();
        }
    }

    public String delete(Long likeId) {
        Likes likes = likeRepository.findById(likeId).orElseThrow(ContentNotFoundException::new);
        likeRepository.delete(likes);
        return "like " + likes + "deleted";
    }
}
