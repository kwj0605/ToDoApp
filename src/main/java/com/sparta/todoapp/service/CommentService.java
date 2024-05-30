package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.CommentRequestDto;
import com.sparta.todoapp.dto.CommentResponseDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Schedule;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto createComment(CommentRequestDto requestDto, Long scheduleId) {
        // 선택한 일정의 ID를 입력 받지 않은 경우
        if(scheduleId == null){
            throw new IllegalArgumentException("선택한 일정의 ID를 입력 받지 못했습니다.");
        }
        // 선택한 일정이 DB에 저장되어 있어야 한다.
        scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정이 존재하지 않습니다.")
        );

        // RequestDto -> Entity
        Comment comment = new Comment(requestDto);

        // DB 저장
        commentRepository.save(comment);

        // Entity -> ScheduleResponseDto
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return commentResponseDto;
    }

    @Transactional
    public String updateComment(Long scheduleId, Long commentId, CommentRequestDto requestDto) {
        // 선택한 일정이나 댓글의 ID를 입력 받지 않은 경우
        if(scheduleId == null || commentId == null){
            throw new IllegalArgumentException("선택한 일정이나 댓글의 ID를 입력 받지 못했습니다.");
        }

        // 선택한 일정과 댓글이 DB에 저장되어 있어야 한다.
        scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정이 존재하지 않습니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다.")
        );

        // 댓글 내용 수정
        comment.update(requestDto);

        return comment.getComment(); // 수정된 댓글 반환
    }
}
