package com.bets.friendlybet.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Bet")
@Getter
@Setter
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bet_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "value")
    private String value;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "bet_creator",
            foreignKey = @ForeignKey(name = "fk",
                    foreignKeyDefinition = "FOREIGN KEY (bet_creator) REFERENCES bet(bet_id) ON DELETE SET NULL"))
    private User betCreator;

    @OneToMany(mappedBy = "id.bet")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UserBets> participants;

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

    public Bet(String title, String content, String value, String status, User betCreator, Set<UserBets> participants) {
        this.title = title;
        this.content = content;
        this.value = value;
        this.status = status;
        this.betCreator = betCreator;
        this.participants = participants;
    }

    public Bet(Integer id, String title, String content, String value, String status, User betCreator, Set<UserBets> participants) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.value = value;
        this.status = status;
        this.betCreator = betCreator;
        this.participants = participants;
    }
}
