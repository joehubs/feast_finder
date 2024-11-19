package com.techelevator.dao.dao;

import java.util.List;

import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
