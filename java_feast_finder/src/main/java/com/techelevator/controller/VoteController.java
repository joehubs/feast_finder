package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.VoteDao;
import com.techelevator.model.Vote;

@RestController
@CrossOrigin
public class VoteController {
    @Autowired
    private VoteDao voteDao;

    @RequestMapping(path = "/vote/{id}", method= RequestMethod.GET)
    public Vote getVote(@Valid @PathVariable int id) {
        return voteDao.getVote(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/vote", method= RequestMethod.POST)
    public Vote addVote(@Valid @RequestBody Vote vote, Principal principal) {
        return voteDao.addVote(vote, principal.getName());
    }

    @RequestMapping(path = "/votes", method= RequestMethod.GET)
    public List<Vote> getAllVotes(Principal principal) {
        return voteDao.getAllVotes(principal.getName());
    }
}