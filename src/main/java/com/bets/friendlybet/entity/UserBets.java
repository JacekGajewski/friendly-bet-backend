package com.bets.friendlybet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UserBets")
@Getter
@Setter
public class UserBets {

    @EmbeddedId
    private UserBetsId id;

    public UserBets() {
    }

    public UserBets(UserBetsId id) {
        this.id = id;
    }
}
