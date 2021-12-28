package com.pjatk.awps.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "schedule")
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ElementCollection
    List<Date> dates = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    //
//    public Map<AppUser, Integer> getUserToWeekMapping() {
//        return userToWeekMapping;
//    }
//
//    public void setUserToWeekMapping(Map<AppUser, Integer> userToWeekMapping) {
//        this.userToWeekMapping = userToWeekMapping;
//    }
}