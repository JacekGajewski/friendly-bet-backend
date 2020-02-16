package com.bets.friendlybet.controller;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.repository.BetRepository;
import com.bets.friendlybet.service.BetService;
import com.bets.friendlybet.service.BetServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class BetController {

    private BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @GetMapping("/bets")
    public @ResponseBody
    List<Bet> getBets(){
        return betService.getAllBets();
    }

    @GetMapping("/bets/{id}")
    public @ResponseBody Bet getBet(@PathVariable int id){
        return betService.getBet(id);
    }

    @PostMapping("/bets")
    public Bet saveBet(@RequestBody Bet newBet){
        return betService.saveBet(newBet);
    }

    @DeleteMapping("/bets/{id}")
    public void deleteBet(@PathVariable int id){
        betService.deleteBet(id);
    }
}
