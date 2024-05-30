package com.sparta.todoapp.relation;

import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Schedule;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class CommentToScheduleTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("커멘트 투 스케쥴 테스트")
    void test1() {
        Schedule schedule = new Schedule();
        schedule.setManager("11");
        schedule.setPassword("11");
        schedule.setWhatToDo("11");
        schedule.setToDoTitle("11");


        Comment comment = new Comment();
        comment.setComment("22");
        comment.setUserId(22L);
        comment.setSchedule(schedule);  //  외래 키 설정

        scheduleRepository.save(schedule);
        commentRepository.save(comment);
    }
}
