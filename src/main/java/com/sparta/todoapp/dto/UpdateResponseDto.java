package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Schedule;
import lombok.Getter;

@Getter
public class UpdateResponseDto {
    private String toDoTitle;
    private String whatToDo;
    private String manager;

    public UpdateResponseDto(Schedule schedule) {
        this.toDoTitle = schedule.getToDoTitle();
        this.whatToDo = schedule.getWhatToDo();
        this.manager = schedule.getManager();
    }

}
