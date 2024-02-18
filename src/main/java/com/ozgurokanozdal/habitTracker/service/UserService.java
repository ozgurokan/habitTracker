package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.dto.UserCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.dto.UserUpdateRequest;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }



    public List<UserResponse> getAll(){
        // model mapper ve stream kullan
        
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users){
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponses.add(userResponse);
        }

        return userResponses;
    }

    public UserResponse getOneById(long id){

        // hata döndürme işini öğren

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return modelMapper.map(user, UserResponse.class);
        }else{
            throw new RuntimeException("user not found");
        }

    };

    public UserResponse save(UserCreateRequest userCreateRequest){
        // username - email unique olmalı bunu araştır.
        // best practice araştır.

        User user = new User(userCreateRequest.getName(),userCreateRequest.getUsername(),userCreateRequest.getPassword(), userCreateRequest.getEmail());
        user = userRepository.save(user);

        return modelMapper.map(user, UserResponse.class);
    }

    public UserUpdateRequest update(Long userId,UserUpdateRequest userUpdateRequest){
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            User userToUpdate = user.get();
            userToUpdate.setName(userUpdateRequest.getName());
            userToUpdate.setEmail(userUpdateRequest.getEmail());
            userToUpdate.setPassword(userUpdateRequest.getPassword());
            userRepository.save(userToUpdate);
            return modelMapper.map(userToUpdate, UserUpdateRequest.class);
        }else{
            throw new RuntimeException("user not found");
        }


    }

    public void delete(long id){

        userRepository.deleteById(id);
    }
}