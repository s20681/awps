package com.pjatk.awps.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transit {
    @Id
    @GeneratedValue
    Long id;

    @ManyToMany
    List<Address> address;
    boolean isValid;

    @OneToOne
    Schedule schedule;

    @OneToMany
    List<Group> group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }
}
