package com.ozgurokanozdal.habitTracker.service;

import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.LikeListResponse;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.dto.UserUpdateRequest;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.exceptions.UserNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        confirmationTokenService = Mockito.mock(ConfirmationTokenService.class);
        modelMapper = Mockito.mock(ModelMapper.class);

        userService = new UserService(userRepository, modelMapper, bCryptPasswordEncoder, confirmationTokenService);
    }

    @Test
    void shouldReturn_allUsersWithUserResponseList() {
        User u1 = new User("name1", "username1", "password1", "mail1@gmail.com");
        User u2 = new User("name2", "username2", "password2", "mail2@gmail.com");
        User u3 = new User("name3", "username3", "password3", "mail3@gmail.com");
        List<User> users = List.of(u1, u2, u3);
        UserResponse user1 = new UserResponse(1, "username1", "namesurname1");
        UserResponse user2 = new UserResponse(2, "username2", "namesurname2");
        UserResponse user3 = new UserResponse(3, "username3", "namesurname3");
        List<UserResponse> excepted = List.of(user1, user2, user3);

        when(userRepository.findAll()).thenReturn(users);
        when(modelMapper.map(u1, UserResponse.class)).thenReturn(user1);
        when(modelMapper.map(u2, UserResponse.class)).thenReturn(user2);
        when(modelMapper.map(u3, UserResponse.class)).thenReturn(user3);

        List<UserResponse> actual = userService.getAll();

        assertIterableEquals(excepted, actual);
        //
        verify(userRepository).findAll();
        verify(modelMapper).map(u1, UserResponse.class);
        verify(modelMapper).map(u2, UserResponse.class);
        verify(modelMapper).map(u3, UserResponse.class);

    }

    @Test
    void shouldReturn_getAllEmptyList() {
        List<User> users = List.of();
        List<UserResponse> excepted = List.of();

        when(userRepository.findAll()).thenReturn(users);
        List<UserResponse> actual = userService.getAll();

        assertIterableEquals(excepted, actual);
        verify(userRepository).findAll();
    }

    @Test
    void shouldReturn_oneUserWithUserResponse() {
        long id = 1;
        User u1 = new User("name1", "username1", "password1", "mail1@gmail.com");
        UserResponse expectedUser = new UserResponse(1, "username1", "namesurname1");

        when(userRepository.findById(id)).thenReturn(Optional.of(u1));
        when(modelMapper.map(u1, UserResponse.class)).thenReturn(expectedUser);

        UserResponse actual = userService.getOneById(id);

        assertEquals(expectedUser, actual);
        verify(userRepository).findById(id);
        verify(modelMapper).map(u1, UserResponse.class);
    }

    @Test
    void shouldReturn_userResponseWhenUserUpdateSuccessfully() {
        long id = 1;
        User u1 = new User(id, "name1", "username1", "password1", "mail1@gmail.com");
        UserResponse expectedUser = new UserResponse(id, "newName", "namesurname1");
        UserUpdateRequest updateReq = new UserUpdateRequest("newName", "newPassword", "newEmail@gmai.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(u1));
        u1.setName(updateReq.getName());
        when(userRepository.save(u1)).thenReturn(u1);
        when(modelMapper.map(u1, UserResponse.class)).thenReturn(expectedUser);

        UserResponse actual = userService.update(id, updateReq);
        assertEquals(expectedUser, actual);
        verify(userRepository).findById(id);
        verify(userRepository).save(u1);
        verify(modelMapper).map(u1, UserResponse.class);

    }

    @Test
    void shouldReturn_stringIncludesUserIdWhenUserDeletedSuccessfully() {
        long id = 1;
        String expected = "User -> " + id + " deleted";
        User u1 = new User(id, "name1", "username1", "password1", "mail1@gmail.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(u1));

        String actual = userService.delete(id);

        assertEquals(expected, actual);
        verify(userRepository).findById(id);

    }

    @Test
    void shouldReturn_HabitResponseListWhenGetUserHabitListByUsernameSuccessfully() {
        List<LikeListResponse> likes = List.of();
        String username = "username1";
        User u1 = new User(1, "name1", username, "password1", "mail1@gmail.com");
        UserResponse userRes = new UserResponse(1, u1.getUsername(), u1.getName());
        Habit habit1 = new Habit("habit1", u1);
        Habit habit2 = new Habit("habit2", u1);
        Habit habit3 = new Habit("habit3", u1);
        u1.setHabitList(List.of(habit1, habit2, habit3));
        HabitResponse habitResponse1 = new HabitResponse(1, "habit1", userRes, likes);
        HabitResponse habitResponse2 = new HabitResponse(2, "habit2", userRes, likes);
        HabitResponse habitResponse3 = new HabitResponse(3, "habit3", userRes, likes);
        List<HabitResponse> expected = List.of(habitResponse1, habitResponse2, habitResponse3);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(u1));
        when(modelMapper.map(habit1, HabitResponse.class)).thenReturn(habitResponse1);
        when(modelMapper.map(habit2, HabitResponse.class)).thenReturn(habitResponse2);
        when(modelMapper.map(habit3, HabitResponse.class)).thenReturn(habitResponse3);

        List<HabitResponse> actual = userService.getUserHabitList(username);

        assertEquals(expected, actual);
        verify(userRepository).findByUsername(username);
    }


    @Test
    void shouldReturn_userByUsername() {
        String username = "username";
        Optional<User> user = Optional.of(new User("user1", username, "password1", "usermail@gmail.com"));

        when(userRepository.findByUsername(username)).thenReturn(user);

        Optional<User> actual = userService.getByUsername(username);

        assertEquals(user, actual);
        verify(userRepository).findByUsername(username);

    }

    @Test
    void shouldReturn_userByUserEmail() {
        String email = "mail1@gmail.com";
        User user = new User("user1", "username1", "password1", email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> actual = userService.getByEmail(email);

        assertEquals(user, actual.get());
    }

    @Test
    void shouldThrowUserNotFoundException_whenUserIdNotExist() {
        long id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> userService.getOneById(id)).isInstanceOf(UserNotFoundException.class);

        verify(userRepository).findById(id);
    }


}