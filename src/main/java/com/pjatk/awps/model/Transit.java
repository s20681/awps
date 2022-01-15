package com.pjatk.awps.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transit {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<TransitUser> transitUsers = new ArrayList<>();

    @OneToMany
    private List<TransitAddress> transitAddresses = new ArrayList<>();

    @ElementCollection
    private List<LocalDateTime> schedule = new ArrayList<>();

    private byte seats = 5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TransitUser> getTransitUsers() {
        return transitUsers;
    }

    public void setTransitUsers(List<TransitUser> transitUsers) {
        this.transitUsers = transitUsers;
    }

    public List<TransitAddress> getTransitAddresses() {
        return transitAddresses;
    }

    public void setTransitAddresses(List<TransitAddress> transitAddresses) {
        this.transitAddresses = transitAddresses;
    }

    public List<LocalDateTime> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<LocalDateTime> schedule) {
        this.schedule = schedule;
    }

    public byte getSeats() {
        return seats;
    }

    public void setSeats(byte seats) {
        this.seats = seats;
    }
}
