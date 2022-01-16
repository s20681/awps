package com.pjatk.awps.model;

import com.pjatk.awps.model.enums.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TransitUser {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    Transit transit;

    private Long personId;
    Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long userId) {
        this.personId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getTransitId() {
        return transit.getId();
    }

    public void setTransit(Transit transit) {
        this.transit = transit;
    }
}
