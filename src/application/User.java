package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;

public class User {


	private SimpleStringProperty userName = new SimpleStringProperty();
	private SimpleStringProperty password= new SimpleStringProperty();
	private SimpleStringProperty firstName= new SimpleStringProperty();
	private SimpleStringProperty lastName= new SimpleStringProperty();
	private SimpleStringProperty email= new SimpleStringProperty();
	private SimpleStringProperty phone= new SimpleStringProperty();
	private SimpleStringProperty shippingAddress= new SimpleStringProperty();
	private SimpleIntegerProperty userType = new SimpleIntegerProperty();
	private ArrayList<Book> booksInCart = new ArrayList<>();


	public User() {
	}
	

	

	public User(ResultSet userData) throws SQLException {
		if(userData.next()) {
			setUserName(userData.getString("user_name"));
			setPassword(userData.getString("pass_word"));
			setFirstName(userData.getString("first_name"));
			setLastName(userData.getString("last_name"));
			setEmail(userData.getString("e_mail"));
			setPhone(userData.getString("phone"));
			setShippingAddress(userData.getString("shipping_address"));
			setUserType(userData.getInt("user_type"));
		}
	}
	public String getUserName() {
		return userName.get();
	}
	public void setUserName(String userName) {
		this.userName.set(userName);
	}
	public String getPassword() {
		return password.get();
	}
	public void setPassword(String password) {
		this.password.set(password);
	}
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);;
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String phone) {
		this.phone.set(phone);
	}
	public String getShippingAddress() {
		return shippingAddress.get();
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress.set(shippingAddress);
	}
	public int getUserType() {
		return userType.get();
	}
	public void setUserType(int userType) {
		this.userType.set(userType);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
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
