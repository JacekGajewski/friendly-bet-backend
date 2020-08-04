package com.bets.friendlybet.entity;


import com.bets.friendlybet.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authority")
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "authority_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private UserRole name;

    @OneToMany(mappedBy = "id.authority")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<UsersAuthorities> usersAuthorities;

    public Authority() {
    }

    public Authority(UserRole name) {
        this.name = name;
    }

    public Authority(UserRole name, Set<UsersAuthorities> usersAuthorities) {
        this.name = name;
        this.usersAuthorities = usersAuthorities;
    }
}


