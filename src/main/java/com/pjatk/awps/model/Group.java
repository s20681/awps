package com.pjatk.awps.model;

import com.pjatk.awps.model.enums.Location;

import javax.persistence.*;
import java.util.List;

@Entity(name = "usergroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    Schedule schedule;

    @OneToOne
    Destination destination;

    Location location;

    @OneToMany
    private List<AppUser> appUsers;

    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<AppUser> getUsers() {
        return appUsers;
    }

    public void setUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
