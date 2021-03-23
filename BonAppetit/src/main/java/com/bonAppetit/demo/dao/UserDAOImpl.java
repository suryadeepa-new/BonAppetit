package com.bonAppetit.demo.dao;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bonAppetit.demo.bean.Response;
import com.bonAppetit.demo.bean.Restaurant;
import com.bonAppetit.demo.bean.User;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private NamedParameterJdbcTemplate  namedjdbcTemplate;
	private JdbcTemplate jdbc;
    

	public User findByUsername(String username){
    	String sql = "SELECT u.username AS name, u.password AS pass, a.authority AS role FROM "+
    			     "[DEMO].[dbo].[User] u INNER JOIN [DEMO].[dbo].[Authority] a on u.username=a.username WHERE "+
    			     "u.enabled =1 and u.username =:userName";
    
    	User userInfo = namedjdbcTemplate.queryForObject(sql,
    			new MapSqlParameterSource("userName", username),
    			new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
               	user.setUsername(rs.getString("name"));
                 user.setPassword(rs.getString("pass"));
                  user.setRole("role");
                return user;
            } 
    	}
    	);
    	
    	return userInfo;
    }
    
	@Override
	public String getUserRole(User user) {
		
		return user.getRole();
	}

	@Override
	public Response saveOwner(User owner, Restaurant outlet) {
	    int success = namedjdbcTemplate.update(
                "insert into [DEMO].[dbo].[User](username,password,enabled,mobileNo,emailId) values(:username,:password,:enabled,:mobileNo,:emailId) ",
                new BeanPropertySqlParameterSource(owner));
	    int restaurantId = namedjdbcTemplate.update(
                "insert into [DEMO].[dbo].[Restaurant](name,location,price_for_two) values(:name,:location,:priceForTwo)",
                new BeanPropertySqlParameterSource(outlet));
	    int userId = namedjdbcTemplate.queryForObject(
                "select user_id from [DEMO].[dbo].[User] where username = :username",
                new MapSqlParameterSource("username", owner.getUsername()),
                (rs, rowNum) ->rs.getInt(1));
	    Response obj = new Response();
	    obj.setRestaurantId(restaurantId);
	    obj.setUserId(userId);
	    obj.setRoleId(owner.getRoleId());
		return null;
	    
    }
		


	@Override
	public Response save(User commonUser) {
		
	    int success = namedjdbcTemplate.update(
                "insert into [DEMO].[dbo].[User](username,password,enabled,mobileNo,emailId) values(:username,:password,:enabled,:mobileNo,:emailId) ",
                new BeanPropertySqlParameterSource(commonUser));
	    int userId =  (int) namedjdbcTemplate.queryForObject(
                "select user_id from [DEMO].[dbo].[User] where username = :username",
                new MapSqlParameterSource("username", commonUser.getUsername()),
                (rs, rowNum) ->rs.getInt(1));
	    Response resp = new Response();
	    resp.setUserId(userId);
	    resp.setRoleId(commonUser.getRoleId());
	    return resp;
	    
	}

}
