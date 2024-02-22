package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.config.PasswordEncoder;
import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.UserCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.dto.UserUpdateRequest;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HabitRepository habitRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper,HabitRepository habitRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.habitRepository = habitRepository;
        this.passwordEncoder = passwordEncoder;

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
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);


    };

    public UserResponse save(UserCreateRequest userCreateRequest){
        // username - email unique olmalı bunu araştır.
        // best practice araştır.
        User user = new User(userCreateRequest.getName(),userCreateRequest.getUsername(),passwordEncoder.bCryptPasswordEncoder().encode(userCreateRequest.getPassword()), userCreateRequest.getEmail());
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
            throw new EntityNotFoundException();
        }


    }

    public String delete(long id){
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(user);
        return "User -> " + id + " deleted";
    }

    public List<HabitResponse> getUserHabitList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Habit> habitList =  user.getHabitList();
        return habitList.stream().map(habit -> modelMapper.map(habit, HabitResponse.class)).collect(Collectors.toList());
    }

    public User getByUsername(String username){

        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }
}
