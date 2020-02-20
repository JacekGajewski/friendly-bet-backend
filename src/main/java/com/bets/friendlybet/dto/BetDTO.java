package com.bets.friendlybet.dto;

public class BetDTO {

    private int betId;
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

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getRivalName() {
        return rivalName;
    }

    public void setRivalName(String rivalName) {
        this.rivalName = rivalName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
