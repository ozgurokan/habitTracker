package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.UserCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.dto.UserUpdateRequest;
import com.ozgurokanozdal.habitTracker.entity.ConfirmationToken;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.exceptions.UserNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    public UserService(UserRepository userRepository, ModelMapper modelMapper,BCryptPasswordEncoder bCryptPasswordEncoder,ConfirmationTokenService confirmationTokenService){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;

    }



    public List<UserResponse> getAll(){
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    public Page<UserResponse> getAllWithPage(Pageable pageable) {
        return userRepository.findAll(pageable).map(e -> modelMapper.map(e, UserResponse.class));
    }

    public UserResponse getOneById(long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    };

    public String save(UserCreateRequest userCreateRequest){

        User user = new User(
                    userCreateRequest.getName(),
                    userCreateRequest.getUsername(),
                    bCryptPasswordEncoder.encode(userCreateRequest.getPassword()),
                    userCreateRequest.getEmail());
        user = userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public UserResponse update(Long userId,UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        user.setName(userUpdateRequest.getName());
        user.setEmail(userUpdateRequest.getEmail());
        user.setPassword(userUpdateRequest.getPassword());
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    public String delete(long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return "User -> " + id + " deleted";
    }

    public List<HabitResponse> getUserHabitList(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return user.getHabitList().stream().map(habit -> modelMapper.map(habit, HabitResponse.class)).collect(Collectors.toList());
    }

    public Optional<User> getByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUsername(username);
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public int enableUser(String email) {
        return userRepository.enabledUser(email);
    }


    public UserResponse getOneByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    }
}
