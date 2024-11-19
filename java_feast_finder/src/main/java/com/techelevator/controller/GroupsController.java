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

import com.techelevator.dao.dao.GroupsDao;
import com.techelevator.model.Groups;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class GroupsController {

    @Autowired
    private GroupsDao groupsDao;
    
    //return group by id
    @RequestMapping(path = "/group/{id}", method= RequestMethod.GET)
    public Groups getGroup(@Valid @PathVariable int id) {
        return groupsDao.getGroup(id);
    }
    //call all of groups from that user
    @RequestMapping(path = "/groups", method = RequestMethod.GET)
    public List<Groups> getGroups(Principal principal) {
        return groupsDao.getGroups(principal.getName());
    }

    @RequestMapping(path = "/groups", method = RequestMethod.POST)
    public Groups addGroup(@Valid @RequestBody Groups group, Principal principal) {
        return groupsDao.addGroup(group, principal.getName());
    }
}