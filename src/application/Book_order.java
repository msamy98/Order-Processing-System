package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book_order {
	private SimpleIntegerProperty ISBN;
	private SimpleStringProperty publisherName;
	private SimpleStringProperty title;
	private SimpleIntegerProperty quantity;
    private SimpleStringProperty date ;

    public String getDate()
    {
    	return date.get();
    }
    public void setDate(String date)
    {
    	this.date.set(date);
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
	public int getQuantity() {
		return quantity.get();
	}
	public void setQuantity(int quantity) {
		this.quantity .set(quantity);
	}
}
