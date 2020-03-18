package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Integer> {

    @Query("SELECT b FROM Bet b WHERE b.betCreator.id = :creatorId OR b.betRival.id = :rivalId")
    List<Bet> getAllByBetCreatorOrBetRival(@Param("creatorId") int creatorId, @Param("rivalId") int rivalId);

    @Query("SELECT b FROM Bet b WHERE b.status = :status AND (b.betCreator.id = :creatorId OR b.betRival.id = :rivalId)")
    List<Bet> findUserBetsWithStatus(@Param("status") String status,
                                     @Param("creatorId") int creatorId, @Param("rivalId") int rivalId);

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
