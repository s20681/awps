package com.pjatk.awps.model;

import com.pjatk.awps.model.enums.DestinationType;
import com.pjatk.awps.model.enums.Location;

import javax.persistence.*;

@Table(name = "destination")
@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private Enum<Location> locationEnum;
    private Enum<DestinationType> destinationTypeEnum;

    public Destination() {
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

    public Enum<Location> getLocationEnum() {
        return locationEnum;
    }

    public void setLocationEnum(Enum<Location> locationEnum) {
        this.locationEnum = locationEnum;
    }

    public Enum<DestinationType> getDestinationTypeEnum() {
        return destinationTypeEnum;
    }

    public void setDestinationTypeEnum(Enum<DestinationType> destinationTypeEnum) {
        this.destinationTypeEnum = destinationTypeEnum;
    }
}