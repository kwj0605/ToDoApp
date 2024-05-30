package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)       // comment 에다가 참조를 설정해주는 이유: 스케쥴이 무조건 있어야 커멘트를 달 수 있다. 만약 schedule에 comment를 참조하면 comment가 없을 수도 있기 때문에
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;


    public Comment(CommentRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.comment = requestDto.getComment();
    }
}