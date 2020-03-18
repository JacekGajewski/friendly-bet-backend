package com.bets.friendlybet.listener;

import com.bets.friendlybet.entity.User;
import org.hibernate.FlushMode;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserEventListener implements PreDeleteEventListener {

    public static final DeleteUserEventListener INSTANCE =
            new DeleteUserEventListener();

    @Override
    public boolean onPreDelete(
            PreDeleteEvent event) {
        final Object entity = event.getEntity();
        System.out.println("DeleteUserEventListener");
        if(entity instanceof User) {
            User user = (User) entity;
            System.out.println("DeleteUserEventListener2");

            event.getSession().createNativeQuery(
                    "UPDATE bet " +
                            "SET bet_creator = null " +
                            "WHERE bet_creator = :id")
                    .setParameter("id", user.getId())
                    .setFlushMode(FlushMode.MANUAL)
                    .executeUpdate();
        }

        return false;
    }
}
