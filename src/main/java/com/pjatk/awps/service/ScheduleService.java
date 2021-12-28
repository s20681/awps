package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.Group;
import com.pjatk.awps.model.Schedule;
import com.pjatk.awps.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    //initiates new schedule with few placeholder dates
    public ResponseEntity<Schedule> create(HttpSession httpSession, Group group){
        if(httpSession.getAttribute("user") == null){
            throw new ApiRequestException("You must be logged in to create a new schedule");
        }

        if(group.getSchedule() == null){
            group.setSchedule(new Schedule());
        }

        if(group.getSchedule().getDates().isEmpty()){
            Date date = new Date(0);
            for (int i = 0; i < 5; i++) {
                group.getSchedule().getDates().add(date);
            }
        }

        scheduleRepository.save(group.getSchedule());
        return ResponseEntity.ok(group.getSchedule());
    }

//    public Destination save(Destination destination){
//        return destinationRepository.save(destination);
//    }
//
}
