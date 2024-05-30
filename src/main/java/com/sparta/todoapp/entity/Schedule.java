package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String toDoTitle;

    @Column(nullable = false)
    private String whatToDo;

    @Column(nullable = false)
    private String manager;

    @Column(nullable = false)
    private String password;

    public Schedule(ScheduleRequestDto requestDto) {
        this.toDoTitle = requestDto.getToDoTitle();
        this.whatToDo = requestDto.getWhatToDo();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.toDoTitle = requestDto.getToDoTitle();
        this.whatToDo = requestDto.getWhatToDo();
        this.manager = requestDto.getManager();
    }
}
