package com.bonAppetit.demo.service;

import java.util.HashSet;

import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.bonAppetit.demo.bean.Response;
import com.bonAppetit.demo.bean.Restaurant;
import com.bonAppetit.demo.bean.User;
import com.bonAppetit.demo.bean.UserDto;
import com.bonAppetit.demo.dao.UserDAO;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@Service(value="userService")
public class UserServiceImpl implements UserDetailsService, UserService{




	    @Autowired
	    private UserDAO userDao;

	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userDao.findByUsername(username);
	     
	        if(user == null){
	            throw new UsernameNotFoundException("Invalid username or password.");
	        }
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	        
	    }

	    private Set<SimpleGrantedAuthority> getAuthority(User user) {
	      	
	    	Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	    	authorities.add(new SimpleGrantedAuthority(userDao.getUserRole(user)));
	     	return authorities;
	    }

		@Override
		public Response save(UserDto user) {
			User commonUser = new User();
			commonUser.setEmailId(user.getEmail());
			commonUser.setMobileNo(user.getPhone());
			commonUser.setUsername(user.getUsername());
			Response resp = new Response();
		switch(user.getUsertype()) {
		case 1:
			
			commonUser.setRoleId(1);
			Restaurant outlet = new Restaurant();
			outlet.setLocation(user.getCity());
			outlet.setName(user.getRestaurantName());
			outlet.setPriceForTwo(user.getPriceForTwo());
			
			resp = userDao.saveOwner(commonUser,outlet);
			
			
			break;
		case 2:
			
			commonUser.setRoleId(2);
			commonUser.setPassword(user.getPassword());
			resp = userDao.save(commonUser);
			
			
			break;
			default:
				break;
		}
			return resp;
		}

		@Override
		public List<User> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public User findOne(String username) {
			// TODO Auto-generated method stub
			return null;
		}

	


}
