package com.techelevator.dao.dao;

import java.util.List;

import com.techelevator.model.Groups;

public interface GroupsDao {
    Groups getGroup(int id);
    List<Groups> getGroups(String name);
    Groups addGroup(Groups group, String name);
}
