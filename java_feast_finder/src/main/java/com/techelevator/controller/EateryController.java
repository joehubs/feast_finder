package com.techelevator.controller;

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

import com.techelevator.dao.dao.EateryDao;
import com.techelevator.model.Eatery;

@RestController
@CrossOrigin
public class EateryController {
    @Autowired
    private EateryDao eateryDao;
    
    @RequestMapping(path = "/eatery/{id}", method= RequestMethod.GET)
    public Eatery getEatery(@Valid @PathVariable int id) {
        return eateryDao.getEatery(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/eatery/{voteId}", method= RequestMethod.POST)
    public Eatery addEatery(@Valid @RequestBody Eatery eatery, @PathVariable int voteId) {
        return eateryDao.addEatery(eatery, voteId);
    }

    @RequestMapping(path = "/eateries/{voteId}", method= RequestMethod.GET)
    public List<Eatery> getEateries(@Valid @PathVariable int voteId) {
        return eateryDao.getEateries(voteId);
    }
}