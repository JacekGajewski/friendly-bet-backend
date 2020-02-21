package com.bets.friendlybet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Bet")
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

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getBetCreator() {
        return betCreator;
    }

    public void setBetCreator(User betCreator) {
        this.betCreator = betCreator;
    }

    public User getBetRival() {
        return betRival;
    }

    public void setBetRival(User betRival) {
        this.betRival = betRival;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
