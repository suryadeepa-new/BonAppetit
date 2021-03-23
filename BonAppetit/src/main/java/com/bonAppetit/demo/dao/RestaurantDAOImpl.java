package com.bonAppetit.demo.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bonAppetit.demo.bean.Menu;

public class RestaurantDAOImpl implements RestaurantDAO{
	
	@Autowired
	private NamedParameterJdbcTemplate  namedjdbcTemplate;
	
	@Override
	public int bulkInsertMenuItems(List<Menu> data) {
		List<Integer> result = null;
		try {
		result = data.stream().map(record->namedjdbcTemplate.update(
                "insert into [DEMO].[dbo].[Menu](menu_item,menu_desc,prep_time,restaurant_id) values(:menuItem,:menuDesc,:prepTime,:restaurantId) ",
                new BeanPropertySqlParameterSource(record))).collect(Collectors.toList());
	
	}catch(Exception e) {
		e.printStackTrace();
	}
		return result.size();
	}
}
