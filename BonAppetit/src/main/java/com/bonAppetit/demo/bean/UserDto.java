package com.bonAppetit.demo.bean;

public class UserDto {
	
	
	  	private String username;
	    private String password;
	    private String email;
	    private String phone;
	    private String name;
	    private int usertype;					// 1 for owner, 0 for customer.
	    
	    private String restaurantName;
	    private double priceForTwo;
	    private String city;

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

		public int getUsertype() {
			return usertype;
		}

		public void setUsertype(int usertype) {
			this.usertype = usertype;
		}

		public String getRestaurantName() {
			return restaurantName;
		}

		public void setRestaurantName(String restaurantName) {
			this.restaurantName = restaurantName;
		}



		public double getPriceForTwo() {
			return priceForTwo;
		}

		public void setPriceForTwo(double priceForTwo) {
			this.priceForTwo = priceForTwo;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

	   

	
}
