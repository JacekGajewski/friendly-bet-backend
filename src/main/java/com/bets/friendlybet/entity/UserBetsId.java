package com.bets.friendlybet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class UserBetsId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_bet")
    private Bet bet;

    public UserBetsId() {
    }

    public UserBetsId(User user, Bet bet) {
        this.user = user;
        this.bet = bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBetsId that =(UserBetsId) o;

        if (!user.getId().equals((that.user.getId()))) return false;
        return bet.getId().equals(((UserBetsId) o).getBet().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBet(), getUser());
    }
}
