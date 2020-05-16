package application;

import java.sql.*;


public class Book {
	private int ISBN;
	private String title;
	private String publisherName;
	private String publishingYear;
	private int price;
	private int bookCategory;
	private int quantity;
	private int threshold;
	private int noOfCopiesInCart;
	
	public Book(ResultSet bookData) throws SQLException {
		ResultSetMetaData metaData = bookData.getMetaData();
		while(bookData.next()) {
			for (int i=1 ; i<=metaData.getColumnCount() ; i++) {
				switch (i) {
				case 1: this.ISBN = bookData.getInt(i);
					break;
				case 2: this.title = bookData.getString(i);
					break;
				case 3: this.publisherName = bookData.getString(i);
					break;
				case 4: this.publishingYear = bookData.getString(i);
					break;
				case 5: this.price = bookData.getInt(i);
					break;
				case 6: this.bookCategory = bookData.getInt(i);
					break;
				case 7: this.quantity = bookData.getInt(i);
					break;
				case 8: this.threshold = bookData.getInt(i);
					break;
				default:
					break;
				}
			}
		}
		bookData.close();
		this.noOfCopiesInCart = 0;
	}
	
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublishingYear() {
		return publishingYear;
	}
	public void setPublishingYear(String publishingYear) {
		this.publishingYear = publishingYear;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(int bookCategory) {
		this.bookCategory = bookCategory;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public int getNoOfCopiesInCart() {
		return noOfCopiesInCart;
	}
	public void setNoOfCopiesInCart(int noOfCopiesInCart) {
		this.noOfCopiesInCart = noOfCopiesInCart;
	}
	
}
