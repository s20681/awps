package com.pjatk.awps.model;

import com.pjatk.awps.model.enums.Location;
import com.pjatk.awps.model.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(name = "usergroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToOne
    Schedule schedule;

    @OneToOne
    Destination destination;

    @ManyToOne
    Transit transit;

    Location location;

    @OneToMany
    private List<AppUser> appUsers = new ArrayList<>();

    @ElementCollection
    private Map<Long, Role> roleMap = new HashMap<>();

    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Transit getTransit() {
        return transit;
    }

    public void setTransit(Transit transit) {
        this.transit = transit;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public Map<Long, Role> getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map<Long, Role> roleMap) {
        this.roleMap = roleMap;
    }
}
