package com.bonAppetit.demo.bean;

public class Menu {
	
	private int menuId;
	private String menuItem;
	private String menuDesc;
	private Double prepTime;
	private int restaurantId;
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public Double getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(Double prepTime) {
		this.prepTime = prepTime;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	

}
