package com.bets.friendlybet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "UsersAuthorities")
@Getter
@Setter
public class UsersAuthorities {

    @EmbeddedId
    private UserAuthoritiesId id;

    public UsersAuthorities() {
    }

    public UsersAuthorities(UserAuthoritiesId id) {
        this.id = id;
    }
}
