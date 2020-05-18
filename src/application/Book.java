package application;

import java.sql.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Book {
	private SimpleIntegerProperty ISBN;
	private SimpleStringProperty title;
	private SimpleStringProperty publisherName;
	private SimpleStringProperty publishingYear;
	private SimpleIntegerProperty price;
	private SimpleStringProperty bookCategory;
	private SimpleIntegerProperty quantity;
	private SimpleIntegerProperty threshold;
	private SimpleIntegerProperty noOfCopiesInCart;
	public Book()
	{
		this.ISBN = new SimpleIntegerProperty ();
		this.title = new SimpleStringProperty();
		this.publisherName = new SimpleStringProperty();
		this.publishingYear= new SimpleStringProperty();
		this.price = new SimpleIntegerProperty ();
		this.bookCategory = new SimpleStringProperty();
		this.quantity = new SimpleIntegerProperty ();
		this.threshold = new SimpleIntegerProperty ();
		this.noOfCopiesInCart = new SimpleIntegerProperty();
	}
	public Book(int ISBN,String title,String publisherName, String publishingYear,int price,String bookCategory
			,int quantity,int threshold,int noOfCopiesInCart)
	{
		this.ISBN = new SimpleIntegerProperty (ISBN);
		this.title = new SimpleStringProperty(title);
		this.publisherName = new SimpleStringProperty(publisherName);
		this.publishingYear= new SimpleStringProperty(publishingYear);
		this.price = new SimpleIntegerProperty (price);
		this.bookCategory = new SimpleStringProperty(bookCategory);
		this.quantity = new SimpleIntegerProperty (quantity);
		this.threshold = new SimpleIntegerProperty (threshold);
		this.noOfCopiesInCart = new SimpleIntegerProperty(noOfCopiesInCart);
	}
		
		


	public int getISBN() {
		return ISBN.get();
	}
	public void setISBN(int iSBN) {
		ISBN.set( iSBN);
	}
	public String getTitle() {
		return title.get();
	}
	public void setTitle(String title) {
		this.title .set( title);
	}
	public String getPublisherName() {
		return publisherName.get();
	}
	public void setPublisherName(String publisherName) {
		this.publisherName.set( publisherName);
	}
	public String getPublishingYear() {
		return publishingYear.get();
	}
	public void setPublishingYear(String publishingYear) {
		this.publishingYear.set(publishingYear);
	}
	public int getPrice() {
		return price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}
	public String getBookCategory() {
		return bookCategory.get();
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory.set(bookCategory);
	}
	public int getQuantity() {
		return quantity.get();
	}
	public void setQuantity(int quantity) {
		this.quantity .set(quantity);
	}
	public int getThreshold() {
		return threshold.get();
	}
	public void setThreshold(int threshold) {
		this.threshold .set(threshold);
	}
	public int getNoOfCopiesInCart() {
		return noOfCopiesInCart.get();
	}
	public void setNoOfCopiesInCart(int noOfCopiesInCart) {
		this.noOfCopiesInCart .set(noOfCopiesInCart);
	}

}
