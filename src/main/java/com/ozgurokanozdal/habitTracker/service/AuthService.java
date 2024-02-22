package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.config.PasswordEncoder;
import com.ozgurokanozdal.habitTracker.dto.AuthRequest;
import com.ozgurokanozdal.habitTracker.dto.AuthResponse;
import com.ozgurokanozdal.habitTracker.dto.UserCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService,PasswordEncoder passwordEncoder,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    public AuthResponse login(AuthRequest authRequest){
        // burayı daha sonra kontrol et saçma oldu burası
        Optional<User> user = userRepository.findByUsername(authRequest.username());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));

        if (user.isEmpty()){
            throw new UsernameNotFoundException("incalid username {}" + authRequest.username());
        }
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.username());
            return new AuthResponse(user.get().getUsername(),token, user.get().getId());
        }
        throw new UsernameNotFoundException("invalid username {} " + authRequest.username());
    }

    public UserResponse register(UserCreateRequest userCreateRequest){
        User user = new User(userCreateRequest.getName(),userCreateRequest.getUsername()
                ,passwordEncoder.bCryptPasswordEncoder().encode(userCreateRequest.getPassword())
                ,userCreateRequest.getEmail());

        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
    }






}
