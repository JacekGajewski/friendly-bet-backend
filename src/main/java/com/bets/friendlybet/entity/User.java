package com.bets.friendlybet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String  password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id.user")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UsersAuthorities> authorities;

    @OneToMany(mappedBy = "betCreator")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Bet> betsCreated;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id.user")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserBets> betsParticipated;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
