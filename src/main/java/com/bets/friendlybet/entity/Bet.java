package com.bets.friendlybet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Bet")
@Getter
@Setter
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bet_id")
    private int bet_id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "value")
    private String value;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "bet_creator")
    private User betCreator;

    @ManyToOne
    @JoinColumn(name = "bet_rival")
    private User betRival;

    public Bet() {
    }

    public Bet(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Bet(String title, String content, String value) {
        this.title = title;
        this.content = content;
        this.value = value;
    }

    public Bet(String title, String content, String value, String status, User betCreator, User betRival) {
        this.title = title;
        this.content = content;
        this.value = value;
        this.status = status;
        this.betCreator = betCreator;
        this.betRival = betRival;
    }

    public Bet(int bet_id, String title, String content, String value, String status, User betCreator, User betRival) {
        this.bet_id = bet_id;
        this.title = title;
        this.content = content;
        this.value = value;
        this.status = status;
        this.betCreator = betCreator;
        this.betRival = betRival;
    }
}
