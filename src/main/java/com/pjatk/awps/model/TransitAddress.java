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

    private Long addressId;

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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
