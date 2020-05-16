package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.*;

public class User {
	
	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String shippingAddress;
	private int userType;
	private ArrayList<Book> booksInCart;
	
	
	public User(ResultSet userData) throws SQLException {
		ResultSetMetaData metaData = userData.getMetaData();
		while(userData.next()) {
			for(int i=1 ; i<=metaData.getColumnCount() ; i++) {
				switch (i) {
				case 1: this.userName = userData.getString(i);
					break;
				case 2: this.password = userData.getString(i);
					break;
				case 3: this.firstName = userData.getString(i);
					break;
				case 4: this.lastName = userData.getString(i);
					break;
				case 5: this.email = userData.getString(i);
					break;
				case 6: this.phone = userData.getString(i);
					break;
				case 7: this.shippingAddress = userData.getString(i);
					break;
				case 8: this.userType = userData.getInt(i);
					break;
				default:
					break;
				}
			}
		}
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Book> getBooksInCart() {
		return booksInCart;
	}
	public void setBooksInCart(ArrayList<Book> booksInCart) {
		this.booksInCart = booksInCart;
	}
	public void addInCart(Book book) {
		booksInCart.add(book);
	}
	
	
	

}
