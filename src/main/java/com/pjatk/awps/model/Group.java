package com.pjatk.awps.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "usergroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    public List<AppUser> getUsers() {
        return appUsers;
    }

    public void setUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
}
