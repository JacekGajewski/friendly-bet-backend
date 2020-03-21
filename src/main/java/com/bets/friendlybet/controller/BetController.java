package com.bets.friendlybet.controller;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.service.BetService;
import com.bets.friendlybet.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user/{userId}")
public class BetController {

    private BetService betService;
    private UserService userService;

    public BetController(BetService betService, UserService userService) {
        this.betService = betService;
        this.userService = userService;
    }

    @GetMapping("bets")
    public @ResponseBody
    List<BetDTO> getBets(@PathVariable int userId) {
        return userService.getAllUserBets(userId);
    }

    @GetMapping("/bets/{betId}")
    public @ResponseBody
    BetDTO getBet(@PathVariable int betId) {
        return betService.getBet(betId);
    }

//    TODO: Change to @RequestParam
    @GetMapping("/bets/status/{betStatus}")
    @ResponseBody
    public List<BetDTO> getBetsByStatus(@PathVariable int userId, @PathVariable String betStatus) {
        return userService.getBetsByStatus(userId, betStatus);
    }

    @PutMapping("/bets")
    @ResponseBody
    public BetDTO updateBet(@Valid @RequestBody BetDTO bet) {
        return betService.updateBet(bet);
    }

    @PostMapping("/bets")
    @ResponseStatus(HttpStatus.CREATED)
    public BetDTO saveBet(@Valid @RequestBody BetDTO newBet) {
        return betService.saveBet(newBet);
    }

    @DeleteMapping("/bets/{betId}")
    public void deleteBet(@PathVariable int userId, @PathVariable int betId) {
        betService.deleteBet(betId);
    }
}
