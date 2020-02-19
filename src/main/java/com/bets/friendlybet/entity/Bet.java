package com.bets.friendlybet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Bet")
public class Bet {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bet_id")
    private int bet_id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "value")
    private String value;

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
}
