package com.ozgurokanozdal.habitTracker.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mvc;


    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @BeforeEach
    void setup() {

        this.mvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new UserControllerAdvice()).build();
    }


    private List<User> sampleUsers() {
        User u1 = new User(1, "name1", "username1", "password1", "mail1@gmail.com");
        User u2 = new User(2, "name2", "username2", "password2", "mail2@gmail.com");
        User u3 = new User(3, "name3", "username3", "password3", "mail3@gmail.com");
        return List.of(u1, u2, u3);
    }

    private List<UserResponse> sampleUserResponses() {
        UserResponse userResponse1 = new UserResponse(1, "username1", "name1");
        UserResponse userResponse2 = new UserResponse(2, "username2", "name2");
        UserResponse userResponse3 = new UserResponse(3, "username3", "name3");
        return List.of(userResponse1, userResponse2, userResponse3);
    }


    @Test
    void shouldReturn_getAll_ok() throws Exception {

        List<UserResponse> userResponses = sampleUserResponses();
        when(userService.getAll()).thenReturn(userResponses);


        MockHttpServletResponse response = mvc.perform(get("/api/v1/user").accept(MediaType.APPLICATION_JSON)).andDo(print()).andReturn().getResponse();
        response.setContentType("application/json;charset=UTF-8");

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<UserResponse> actualList = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<UserResponse>>() {
        });

        assertEquals(userResponses.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            UserResponse expectedUserRes = userResponses.get(i);
            UserResponse actualUserRes = actualList.get(i);

            assertAll(() -> assertEquals(expectedUserRes.getId(), actualUserRes.getId()), () -> assertEquals(expectedUserRes.getUsername(), actualUserRes.getUsername()), () -> assertEquals(expectedUserRes.getName(), actualUserRes.getName()));
        }
        verify(userService).getAll();
    }

    @Test
    void shouldReturn_getOneByUsername_ok() throws Exception {
        User u1 = sampleUsers().get(0);
        String username = u1.getUsername();
        UserResponse expected = new UserResponse(1, u1.getUsername(), u1.getName());

        when(userService.getOneByUsername(username)).thenReturn(expected);

        MockHttpServletResponse response = mvc.perform(get("/api/v1/user/{username}", username).accept(MediaType.APPLICATION_JSON)).andDo(print()).andReturn().getResponse();
        response.setContentType("application/json;charset=UTF-8");


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        UserResponse actual = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<UserResponse>() {
        });
        assertAll(() -> assertEquals(expected.getId(), actual.getId()), () -> assertEquals(expected.getUsername(), actual.getUsername()), () -> assertEquals(expected.getName(), actual.getName()));
        verify(userService).getOneByUsername(username);
    }


}