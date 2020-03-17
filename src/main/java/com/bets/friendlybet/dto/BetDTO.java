package com.bets.friendlybet.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BetDTO {

    private int betId;

    @NotBlank(message = "Please provide a bet title")
    @Size(min = 3, max = 50, message = "Bet title contain between 6-30 characters")
    private String title;

    private String content;
    private String value;
    private String status;
    private int creatorId;
    private String rivalName;

    public BetDTO(int betId, String title, String content, String value, String status, int creatorId, String rivalName) {
        this.betId = betId;
        this.title = title;
        this.content = content;
        this.value = value;
        this.status = status;
        this.creatorId = creatorId;
        this.rivalName = rivalName;
    }
}
