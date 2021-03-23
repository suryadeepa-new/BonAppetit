package com.bonAppetit.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bonAppetit.demo.service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@PreAuthorize("hasAuthority('RESTAURANT_OWNER')")
	@PostMapping("/menuUpload")
	public ResponseEntity uploadMenu(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		
	int menuItemsSaved = restaurantService.uploadMenu(file, name);
	return null;		
		
	}
	

}
