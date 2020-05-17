package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Sale {
	private SimpleIntegerProperty ISBN;
	private SimpleStringProperty title;
	private SimpleIntegerProperty totalSale;

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

	public int getTotalSale() {
		return totalSale.get();
	}
	public void setTotalSale(int totalSale) {
		this.totalSale.set( totalSale);
	}
}
