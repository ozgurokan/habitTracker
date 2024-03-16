package com.ozgurokanozdal.habitTracker.service;

import com.ozgurokanozdal.habitTracker.dto.CommentCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.CommentResponse;
import com.ozgurokanozdal.habitTracker.entity.Comment;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.exceptions.ContentNotFoundException;
import com.ozgurokanozdal.habitTracker.exceptions.UserNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.CommentRepository;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository,ModelMapper modelMapper,UserRepository userRepository,HabitRepository habitRepository){
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
    }

    public List<CommentResponse> getAll(){
        return commentRepository.findAll().stream().map(comment ->  modelMapper.map(comment,CommentResponse.class)).collect(Collectors.toList());
    }
    public Page<CommentResponse> getAll(Pageable pageable){
        return commentRepository.findAll(pageable).map(comment ->  modelMapper.map(comment,CommentResponse.class));
    }

    public CommentResponse create(CommentCreateRequest commentCreateRequest){
        Habit habit = habitRepository.findById(commentCreateRequest.habitId()).orElseThrow(ContentNotFoundException::new);
        User user = userRepository.findById(commentCreateRequest.userId()).orElseThrow(UserNotFoundException::new);
        Comment comment =  new Comment(commentCreateRequest.commentText(),user,habit, LocalDateTime.now());

        return modelMapper.map(commentRepository.save(comment),CommentResponse.class);
    }

    public String delete(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(ContentNotFoundException::new);
        commentRepository.delete(comment);
        return "Comment deleted";
    }

    public Page<CommentResponse> getAllByHabitId(Long habitId, Pageable pageable) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        return commentRepository.findAllByHabitId(habitId,pageable).map(comment -> modelMapper.map(comment, CommentResponse.class));
    }


    public Page<CommentResponse> getAllByUserId(String username, Pageable pageable){
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return commentRepository.findAllByUserId(user.getId(),pageable).map(comment -> modelMapper.map(comment, CommentResponse.class));
    }
}
