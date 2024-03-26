package com.ozgurokanozdal.habitTracker.service;

import com.ozgurokanozdal.habitTracker.dto.HabitCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.HabitUpdateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.exceptions.ContentNotFoundException;
import com.ozgurokanozdal.habitTracker.exceptions.UserNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.repository.LikeRepository;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HabitServiceTest {


    private  HabitRepository habitRepository;
    private  ModelMapper modelMapper;
    private  UserRepository userRepository;
    private  ActivityService activityService;
    private  LikeRepository likeRepository;
    private HabitService habitService;

    @BeforeEach
    void setup(){
        habitRepository = Mockito.mock(HabitRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        userRepository = Mockito.mock(UserRepository.class);
        activityService = Mockito.mock(ActivityService.class);
        likeRepository = Mockito.mock(LikeRepository.class);

        habitService = new HabitService(habitRepository,
                modelMapper,userRepository,
                activityService,likeRepository
        );
    }

    @Test
    void shouldReturn_allHabitSuccessfullyWithHabitResponseList(){
        User user = new User(1,"u1","uu1","pass1","email@gmail.com");
        UserResponse userResponse = new UserResponse(1, user.getUsername(), user.getName());

        Habit h1 = new Habit("habit1",user);
        Habit h2 = new Habit("hait2",user);
        Habit h3 = new Habit("habit3",user);
        List<Habit> habits = List.of(h1,h2,h3);
        HabitResponse hr1 = new HabitResponse(1,h1.getName(),userResponse);
        HabitResponse hr2 = new HabitResponse(2,h2.getName(),userResponse);
        HabitResponse hr3 = new HabitResponse(3,h3.getName(),userResponse);
        List<HabitResponse> expected = List.of(hr1,hr2,hr3);


        when(habitRepository.findAll()).thenReturn(habits);
        when(modelMapper.map(h1, HabitResponse.class)).thenReturn(hr1);
        when(modelMapper.map(h2, HabitResponse.class)).thenReturn(hr2);
        when(modelMapper.map(h3, HabitResponse.class)).thenReturn(hr3);

        List<HabitResponse> actual = habitService.getAll();
        assertIterableEquals(expected,actual);
        verify(habitRepository,times(1)).findAll();
        verify(modelMapper).map(h1, HabitResponse.class);
        verify(modelMapper).map(h2, HabitResponse.class);
        verify(modelMapper).map(h3, HabitResponse.class);
    }

    @Test
    void shouldReturn_EmptyHabitResponseListWhenAnyHabitExist(){
        List<Habit> habits = List.of();
        List<HabitResponse> habitResponses = List.of();

        when(habitRepository.findAll()).thenReturn(habits);

        List<HabitResponse> actual = habitService.getAll();

        assertIterableEquals(habitResponses,actual);
        verify(habitRepository,times(1)).findAll();

    }

    @Test
    void shouldReturn_habitResponseWhenSuccessfullyCreateNewHabit(){
        String habitName = "h1";
        long userId = 1;
        HabitCreateRequest habitCreateRequest = new HabitCreateRequest(habitName,userId);
        User user = new User(userId,"u1","uu1","pass1","mail@gmail.com");
        Habit habit = new Habit(habitCreateRequest.getName(),user);
        HabitResponse hResponse = new HabitResponse(1,habit.getName(),
                new UserResponse(user.getId(), user.getUsername(), user.getName())
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(habitRepository.save(any(Habit.class))).thenReturn(habit);
        when(modelMapper.map(habit, HabitResponse.class)).thenReturn(hResponse);

        HabitResponse actual = habitService.create(habitCreateRequest);
        assertEquals(hResponse,actual);
        verify(userRepository).findById(any());
        verify(habitRepository).save(any());
        verify(modelMapper).map(any(),any());
    }

    @Test
    void shouldReturn_habitResponseWhenSuccessfullyUpdateHabit(){
        long habitId = 1;

        String newName = "updatedName";
        User user = new User(1,"u1","uu1","pass1","email@gmail.com");
        UserResponse userResponse = new UserResponse(1, user.getUsername(), user.getName());

        HabitUpdateRequest habitUpdateRequest = new HabitUpdateRequest(newName);
        Habit habit = new Habit(habitId,"name",user);
        Habit updated = new Habit(habit.getId(),newName,habit.getUser());
        HabitResponse expected = new HabitResponse(1,newName,userResponse);

        when(habitRepository.findById(habitId)).thenReturn(Optional.of(habit));
        when(habitRepository.save(habit)).thenReturn(updated);
        when(modelMapper.map(habit, HabitResponse.class)).thenReturn(expected);

        HabitResponse actual = habitService.update(habitId,habitUpdateRequest);
        assertEquals(expected,actual);
        verify(habitRepository).findById(habitId);
        verify(habitRepository).save(habit);
        verify(modelMapper).map(habit, HabitResponse.class);
    }

    @Test
    void shouldReturn_stringMessageWhenSuccessfullyDeleteHabit(){
        long habitId = 1;
        String expectedMsg = "Habit -> " + habitId + " deleted";
        User user = new User(1,"u1","uu1","pass1","email@gmail.com");
        Habit habit = new Habit(habitId,"name",user);

        when(habitRepository.findById(habitId)).thenReturn(Optional.of(habit));
        doNothing().when(habitRepository).delete(habit);

        String actual = habitService.delete(habitId);

        assertEquals(expectedMsg,actual);
        verify(habitRepository).findById(habitId);
        verify(habitRepository).delete(habit);

    }

    @Test
    void shouldThrow_contentNotFoundException_WhenHabitNotFoundByHabitId(){
        long habitId = 1;

        when(habitRepository.findById(habitId)).thenReturn(Optional.empty());

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> habitService.getById(habitId)).isInstanceOf(ContentNotFoundException.class);

        verify(habitRepository).findById(habitId);
    }

}