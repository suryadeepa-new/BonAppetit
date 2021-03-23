package com.bonAppetit.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {
	int uploadMenu(MultipartFile file,String name);
}
