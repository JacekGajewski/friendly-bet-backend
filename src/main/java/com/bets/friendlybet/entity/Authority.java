package com.bets.friendlybet.entity;


import com.bets.friendlybet.security.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Authority() {
    }

    public Authority(UserRole name, User user) {
        this.name = name;
        this.user = user;
    }
}


