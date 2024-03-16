package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.dto.CommentCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.CommentResponse;
import com.ozgurokanozdal.habitTracker.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }


    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAll(){
        return ResponseEntity.ok(commentService.getAll());
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<CommentResponse>> getAll(Pageable pageable){
        return ResponseEntity.ok(commentService.getAll(pageable));
    }

    @GetMapping("/get-habit-comments/{habitId}")
    public ResponseEntity<Page<CommentResponse>> getAllByHabitId(@PathVariable Long habitId,Pageable pageable){
    return ResponseEntity.ok(commentService.getAllByHabitId(habitId,pageable));
    }
    @GetMapping("/get-user-comments/{username}")
    public ResponseEntity<Page<CommentResponse>> getAllByUserId(@PathVariable String username,Pageable pageable){
        return ResponseEntity.ok(commentService.getAllByUserId(username,pageable));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentCreateRequest commentCreateRequest){
        return ResponseEntity.ok(commentService.create(commentCreateRequest));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> delete(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.delete(commentId));
    }
}
