package com.supportportal.service;

import com.supportportal.domain.User;
import com.supportportal.exceptions.domain.UsernameExistsException;
import com.supportportal.exceptions.domain.UsernameNotFountException;

import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UsernameExistsException, UsernameNotFountException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
