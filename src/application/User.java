package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;

public class User {


	private SimpleStringProperty userName;
	private SimpleStringProperty password;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty email;
	private SimpleStringProperty phone;
	private SimpleStringProperty shippingAddress;
	private SimpleIntegerProperty userType;
	private ArrayList<Book> booksInCart;
	

	public User(ResultSet userData) throws SQLException {
		ResultSetMetaData metaData = userData.getMetaData();
		while(userData.next()) {
			for(int i=1 ; i<=metaData.getColumnCount() ; i++) {
				switch (i) {
				case 1: this.userName = new SimpleStringProperty( userData.getString(i));
					break;
				case 2: this.password = new SimpleStringProperty( userData.getString(i));
					break;
				case 3: this.firstName = new SimpleStringProperty( userData.getString(i));
					break;
				case 4: this.lastName = new SimpleStringProperty( userData.getString(i));
					break;
				case 5: this.email =new SimpleStringProperty( userData.getString(i));
					break;
				case 6: this.phone = new SimpleStringProperty( userData.getString(i));
					break;
				case 7: this.shippingAddress = new SimpleStringProperty( userData.getString(i));
					break;
				case 8: this.userType = new SimpleIntegerProperty(userData.getInt(i));
					break;
				default:
					break;
				}
			}
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
