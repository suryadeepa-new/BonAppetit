package com.bonAppetit.demo.dao;

import java.util.List;
import java.util.Map;

import com.bonAppetit.demo.bean.Menu;

public interface RestaurantDAO {
	
	int bulkInsertMenuItems(List<Menu> data);

}
