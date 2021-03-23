package com.bonAppetit.demo.service;

import java.util.List;

import com.bonAppetit.demo.bean.Response;
import com.bonAppetit.demo.bean.User;
import com.bonAppetit.demo.bean.UserDto;

public interface UserService {

    List<User> findAll();
    User findOne(String username);
	Response save(UserDto user);
}
