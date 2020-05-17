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
	private SimpleIntegerProperty bookCategory;
	private SimpleIntegerProperty quantity;
	private SimpleIntegerProperty threshold;
	private SimpleIntegerProperty noOfCopiesInCart;
	public Book()
	{

	}
	public Book(int ISBN,String title,String publisherName, String publishingYear,int price,int bookCategory
			,int quantity,int threshold,int noOfCopiesInCart)
	{
		this.ISBN = new SimpleIntegerProperty (ISBN);
		this.title = new SimpleStringProperty(title);
		this.publisherName = new SimpleStringProperty(publisherName);
		this.publishingYear= new SimpleStringProperty(publishingYear);
		this.price = new SimpleIntegerProperty (price);
		this.bookCategory = new SimpleIntegerProperty (bookCategory);
		this.quantity = new SimpleIntegerProperty (quantity);
		this.threshold = new SimpleIntegerProperty (threshold);
	}
	public Book(ResultSet bookData) throws SQLException {
		ResultSetMetaData metaData = bookData.getMetaData();
		while(bookData.next()) {
			for (int i=1 ; i<=metaData.getColumnCount() ; i++) {
				switch (i) {
				case 1: this.ISBN = new SimpleIntegerProperty (bookData.getInt(i));
					break;
				case 2: this.title = new SimpleStringProperty(bookData.getString(i));
					break;
				case 3: this.publisherName = new SimpleStringProperty(bookData.getString(i));
					break;
				case 4: this.publishingYear= new SimpleStringProperty(bookData.getString(i));
					break;
				case 5: this.price = new SimpleIntegerProperty (bookData.getInt(i));
					break;
				case 6: this.bookCategory = new SimpleIntegerProperty (bookData.getInt(i));
					break;
				case 7: this.quantity = new SimpleIntegerProperty (bookData.getInt(i));
					break;
				case 8: this.threshold = new SimpleIntegerProperty (bookData.getInt(i));
					break;
				default:
					break;
				}
			}
		}
		bookData.close();
		this.noOfCopiesInCart = new SimpleIntegerProperty (0);;
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
	public int getBookCategory() {
		return bookCategory.get();
	}
	public void setBookCategory(int bookCategory) {
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