package com.bonAppetit.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bonAppetit.demo.bean.AuthToken;
import com.bonAppetit.demo.bean.LoginUser;
import com.bonAppetit.demo.bean.Response;

import com.bonAppetit.demo.bean.UserDto;
import com.bonAppetit.demo.config.TokenCreation;
import com.bonAppetit.demo.service.UserService;

@CrossOrigin
@RestController
public class UserController {
	
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private TokenCreation tokenService;

	    @Autowired
	    private UserService userService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }
    
    
    @PostMapping(value="/register")
    public Response saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    
    
    
    
    

}
