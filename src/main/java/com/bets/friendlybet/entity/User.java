package com.bets.friendlybet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String  password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "betCreator")
    @JsonIgnore
    private Set<Bet> betsCreated;

    @OneToMany(mappedBy = "betRival")
    @JsonIgnore
    private Set<Bet> betsAccepted;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Bet> getBetsCreated() {
        return betsCreated;
    }

    public void setBetsCreated(Set<Bet> betsCreated) {
        this.betsCreated = betsCreated;
    }

    public Set<Bet> getBetsAccepted() {
        return betsAccepted;
    }

    public void setBetsAccepted(Set<Bet> betsAccepted) {
        this.betsAccepted = betsAccepted;
    }
}
