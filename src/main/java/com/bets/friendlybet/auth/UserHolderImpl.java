package com.bets.friendlybet.auth;

import org.springframework.stereotype.Component;

interface UserHolder {
    int getId();
    void setId(int id);
}

@Component
public class UserHolderImpl implements UserHolder{

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
