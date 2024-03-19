package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.dto.LikeCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.LikeResponse;
import com.ozgurokanozdal.habitTracker.service.LikeService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/like")
public class LikeController {


    private final LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }


    @GetMapping()
    public ResponseEntity<List<LikeResponse>> getAll(){
        return ResponseEntity.ok(likeService.getAll());
    }

    @GetMapping("/get-user-all-likes/{userId}")
    public ResponseEntity<List<LikeResponse>> getAllByUserId(@PathVariable long userId, Pageable pageable){
        return ResponseEntity.ok(likeService.getAllByUserId(userId,pageable));
    }

    @PostMapping()
    public ResponseEntity<LikeResponse> create(@RequestBody LikeCreateRequest likeCreateRequest){
        return ResponseEntity.ok(likeService.create(likeCreateRequest));
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> delete(@PathVariable Long likeId){
        return ResponseEntity.ok(likeService.delete(likeId));
    }



}
