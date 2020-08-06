package com.bets.friendlybet.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BetDTO {

    private int betId;

    @NotBlank(message = "Please provide a bet title")
    @Size(min = 6, max = 30, message = "Bet title contain between 6-30 characters")
    private String title;

    private String content;
    private String value;
    private String status;
    private int creatorId;
    private List<UserResponseDTO> rivalsName;

    public BetDTO(int betId, String title, String content, String value, String status, int creatorId,  List<UserResponseDTO> rivalsName) {
        this.betId = betId;
        this.title = title;
        this.content = content;
        this.value = value;
        this.status = status;
        this.creatorId = creatorId;
        this.rivalsName = rivalsName;
    }
}
