package com.pjatk.awps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TransitAddress {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    Transit transit;

    @ManyToOne
    Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransitId() {
        return transit.getId();
    }

    public void setTransit(Transit transit) {
        this.transit = transit;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
