package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Integer> {

    List<Bet> getAllByBetCreatorOrBetRival(User creator, User rival);

    List<Bet> findByStatusAndBetCreatorOrStatusAndBetRival(String status, User creator, String theStatus, User rival);

    @Modifying
    @Query("UPDATE Bet SET bet_creator = null WHERE bet_creator = :userId")
    void deleteUserFromCreator(@Param("userId") int userId);

    @Modifying
    @Query("UPDATE Bet SET bet_rival = null WHERE bet_rival = :userId")
    void deleteUserFromRival(@Param("userId") int userId);

    @Modifying
    @Query("UPDATE Bet SET bet_creator = null WHERE bet_id = :betId AND bet_creator = :userId")
    void deleteUserFromCreatorForBet(@Param("userId") int userId, @Param("betId") int betId);

    @Modifying
    @Query("UPDATE Bet SET bet_rival = null WHERE bet_id = :betId AND bet_rival = :userId")
    void deleteUserFromRivalForBet(@Param("userId") int userId, @Param("betId") int betId);

    @Modifying
    @Query("DELETE FROM Bet WHERE bet_id = :betId AND bet_creator = null AND bet_rival = null")
    void deleteBetIfItIsNotAttached(@Param("betId") int betId);

    @Modifying
    @Query("DELETE FROM Bet WHERE bet_creator = null AND bet_rival = null")
    void deleteBetsIfItIsNotAttached();
}
