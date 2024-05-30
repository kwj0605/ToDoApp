package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.CommentRequestDto;
import com.sparta.todoapp.dto.CommentResponseDto;
import com.sparta.todoapp.service.CommentService;
import com.sparta.todoapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/schedule/{scheduleId}/comment")        // 질문: scheduleId를 CommentRequestDto랑 같이 바디로 받을 수 있는 지
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long scheduleId) {
        return commentService.createComment(requestDto, scheduleId);
    }


}
