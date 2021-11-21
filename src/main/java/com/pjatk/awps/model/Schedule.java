package com.pjatk.awps.model;

import javax.persistence.*;
import java.util.Map;

@Table(name = "schedule")
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

//    @ElementCollection
//    private Map<AppUser, Integer> userToWeekMapping;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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