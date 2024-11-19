package com.techelevator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.EateryVotersDao;
import com.techelevator.model.Eatery_Voter;

@RestController
@CrossOrigin
public class EateryVotersControllers {

    @Autowired
    private EateryVotersDao eateryVotersDao;

    @RequestMapping(path = "/eateryVotes/{eateryId}/true", method = RequestMethod.GET)
    public int getEateryTrueVotes(@PathVariable int eateryId) {
        return eateryVotersDao.getEateryTrueVotes(eateryId);
    }

    @RequestMapping(path = "/eateryVotes/{eateryId}/false", method = RequestMethod.GET)
    public int getEateryFalseVotes(@PathVariable int eateryId) {
        return eateryVotersDao.getEateryFalseVotes(eateryId);
    }

    @RequestMapping(path="/addEateryVote", method = RequestMethod.POST)
    public Eatery_Voter addEateryVote(@Valid @RequestBody Eatery_Voter eateryVoter) {
        System.out.println(eateryVoter.getEatery_response() + "\n".repeat(5));
        return eateryVotersDao.addEateryVote(eateryVoter);
    }
}
