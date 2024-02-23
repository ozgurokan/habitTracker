package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.config.PasswordEncoder;
import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.UserCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.dto.UserUpdateRequest;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.exceptions.UserNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
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
import java.util.logging.LogManager;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }


    public List<UserResponse> getAll(){
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    public UserResponse getOneById(long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    };

    public UserResponse save(UserCreateRequest userCreateRequest){
        User user = new User(
                    userCreateRequest.getName(),
                    userCreateRequest.getUsername(),
                    passwordEncoder.bCryptPasswordEncoder().encode(userCreateRequest.getPassword()),
                    userCreateRequest.getEmail()
        );
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    public UserUpdateRequest update(Long userId,UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        user.setName(userUpdateRequest.getName());
        user.setEmail(userUpdateRequest.getEmail());
        user.setPassword(userUpdateRequest.getPassword());
        userRepository.save(user);
        return modelMapper.map(user, UserUpdateRequest.class);
    }

    public String delete(long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return "User -> " + id + " deleted";
    }

    public List<HabitResponse> getUserHabitList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getHabitList().stream().map(habit -> modelMapper.map(habit, HabitResponse.class)).collect(Collectors.toList());
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
