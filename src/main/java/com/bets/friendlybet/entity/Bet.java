package com.bets.friendlybet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

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
    @Column(name = "id_test")
    private int id_test;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "value")
    private String value;

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
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
