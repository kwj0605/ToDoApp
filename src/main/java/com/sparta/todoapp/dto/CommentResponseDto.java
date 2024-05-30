package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private Long userId;
    private String comment;
    private LocalDateTime dateTime;


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.comment = comment.getComment();
        this.dateTime = comment.getCreatedAt();
    }
}
