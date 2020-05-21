package application;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book_order {
	
	private SimpleIntegerProperty ISBN = new SimpleIntegerProperty();;
	private SimpleStringProperty publisherName = new SimpleStringProperty();
	private SimpleStringProperty title = new SimpleStringProperty();
	private SimpleIntegerProperty quantity = new SimpleIntegerProperty() ;
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleIntegerProperty orderId = new SimpleIntegerProperty();
    private SimpleIntegerProperty price = new SimpleIntegerProperty();
    

   

	public Integer getISBN() {
		return ISBN.getValue();
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
	public int getOrderId() {
		return orderId.get();
	}
	public void setOrederId(int orderId) {
		this.orderId.set(orderId);
	}
	public int getPrice() {
		return price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}
	public String getDate() {
		return date.get();
	}
	public void setDate(String date) {
		this.date.set(date);
	}
	
}
