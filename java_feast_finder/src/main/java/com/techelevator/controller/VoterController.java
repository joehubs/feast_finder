package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.VoterDao;
import com.techelevator.model.Voter;

@RestController
@CrossOrigin
public class VoterController {
    
    @Autowired
    private VoterDao voterDao;

    @RequestMapping(path = "/voter/{voter_name}/{vote_id}", method = RequestMethod.POST)
    public Voter addVoter(@PathVariable String voter_name, @PathVariable int vote_id) {
        return voterDao.addVoter(voter_name, vote_id);
    }

    @RequestMapping(path = "/voter/{voter_id}", method = RequestMethod.GET)
    public Voter getVoter(@PathVariable int voter_id) {
        return voterDao.getVoter(voter_id);
    }


}
