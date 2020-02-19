package com.bets.friendlybet.entity;


import com.bets.friendlybet.security.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authority_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private UserRole name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Authority() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


