package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.EateryDao;
import com.techelevator.dao.dao.YelpDao;
import com.techelevator.dao.jdbcdao.JdbcEateryDao;
import com.techelevator.model.Eatery;
import com.techelevator.services.YelpService;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class YelpController {
    @Autowired
    private YelpService yelpService;

    @Autowired
    private YelpDao yelpDao;

    // **************************************************************************************************************
    // this is getting called twice in a row for some reason
    @RequestMapping(path = "/restaurants/{data}", method = RequestMethod.GET)
    public List<Eatery> getEateryList(@Valid @PathVariable String data) {
        return findEateries(data);
    }
    // **************************************************************************************************************

    @Autowired
    private EateryDao eateryDao;

    private List<Eatery> findEateries(String data) {
        if (!yelpDao.checkYelp(data))
            yelpDao.addYelp(data, yelpService.getEateries(data));
        return yelpDao.getEateries(data);
    }
}