package com.bonAppetit.demo.dao;

import com.bonAppetit.demo.bean.Response;
import com.bonAppetit.demo.bean.Restaurant;
import com.bonAppetit.demo.bean.User;

public interface UserDAO {

	
	User findByUsername(String name);
	String getUserRole(User user);
	Response saveOwner(User owner, Restaurant outlet);
	Response save(User commonUser);
}
