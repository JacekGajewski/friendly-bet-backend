package com.bets.friendlybet.dto;

import lombok.Data;

@Data
public class BetDTO {

    private int betId;
    private String title;
    private String content;
    private String value;
    private String status;
    private int creatorId;
    private String rivalName;

    BetDTO(int betId, String title, String content, String value, String status, int creatorId, String rivalName) {
        this.betId = betId;
        this.title = title;
        this.content = content;
        this.value = value;
        this.status = status;
        this.creatorId = creatorId;
        this.rivalName = rivalName;
    }
}
