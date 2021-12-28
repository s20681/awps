package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.Destination;
import com.pjatk.awps.model.Group;
import com.pjatk.awps.model.Schedule;
import com.pjatk.awps.model.Transit;
import com.pjatk.awps.repository.ScheduleRepository;
import com.pjatk.awps.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Service
public class TransitService {
    private TransitRepository transitRepository;

    @Autowired
    public TransitService(TransitRepository transitRepository) {
        this.transitRepository = transitRepository;
    }

    public ResponseEntity<Transit> create(HttpSession httpSession, Transit transit) {
        if(httpSession.getAttribute("user") == null){
            throw new ApiRequestException("You must be logged in to create a new transit");
        }

        return ResponseEntity.ok(save(transit));
    }

    public Transit save(Transit transit) {
        return transitRepository.save(transit);
    }

    public List<Transit> getList() {
        return transitRepository.findAll();
    }
}
