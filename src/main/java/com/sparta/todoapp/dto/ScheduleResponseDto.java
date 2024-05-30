package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Schedule;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String toDoTitle;
    private String whatToDo;
    private String manager;

    @Getter(value = AccessLevel.PRIVATE) // 게터로 가져올 때 숨겨준다.
    private String password;
    private LocalDateTime dateCreated;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.toDoTitle = schedule.getToDoTitle();
        this.whatToDo = schedule.getWhatToDo();
        this.manager = schedule.getManager();
        this.password = schedule.getPassword();
        this.dateCreated = schedule.getCreatedAt(); // 타임스탬프에서 실시간 가져옴
    }

    public ScheduleResponseDto(Long id, String toDoTitle, String whatToDo, String manager, String password, LocalDate dateCreated) {
        this.id = id;
        this.toDoTitle = toDoTitle;
        this.whatToDo = whatToDo;
        this.manager = manager;
        this.password = password;
    }
}
