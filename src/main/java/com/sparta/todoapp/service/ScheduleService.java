package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.ScheduleRequestDto;
import com.sparta.todoapp.dto.ScheduleResponseDto;
import com.sparta.todoapp.dto.UpdateResponseDto;
import com.sparta.todoapp.entity.Schedule;
import com.sparta.todoapp.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service    // bean에 등록
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        scheduleRepository.save(schedule);

        // Entity -> ScheduleResponseDto
        ScheduleResponseDto schedueleResponseDto = new ScheduleResponseDto(schedule);

        return schedueleResponseDto;
    }

    public List<ScheduleResponseDto> getChooseSchedule(Long id) {
        // DB 조회
        return scheduleRepository.findAllById(id).stream().map(ScheduleResponseDto::new).toList();
    }

    public List<ScheduleResponseDto> getSchedule() {
        // DB 조회
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public UpdateResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        // 비밀번호 일치 확인
        checkPassword(schedule, requestDto.getPassword());

        // 스케쥴 내용 수정
        schedule.update(requestDto);

        return new UpdateResponseDto(schedule);
    }


    public Long deleteSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        // 비밀번호 일치 확인
        checkPassword(schedule, requestDto.getPassword());

        // 스케쥴 내용 삭제
        scheduleRepository.delete(schedule);

        return id;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->  // Otionall 타입으로 받아도 되는데 메모로 빼기 위해서
                new IllegalArgumentException("선택한 스케쥴은 존재하지 않습니다."));
    }

    private void checkPassword(Schedule schedule, String password) {
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
