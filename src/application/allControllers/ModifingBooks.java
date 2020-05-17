package application.allControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifingBooks {

	@FXML
	private TextField ISBN;

	@FXML
	private TextField newISBN;

	@FXML
	private TextField title;

	@FXML
	private TextField publisher_name;

	@FXML
	private TextField publisher_year;

	@FXML
	private TextField price;

	@FXML
	private TextField book_category;

	@FXML
	private TextField quantity;

	@FXML
	private TextField threshold;

	@FXML
	private Label error_msg;

	private Stage myStage;

	private Database database = new Database();
	@FXML
	void addNewBooks() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		error_msg.setVisible(false);
		database.databaseConnector();
		database.setQuery("SELECT * FROM order_processing_system.book WHERE ISBN = " + "\'" + ISBN.getText() + "\'" + ";");
		ResultSet result = database.executeRetrieveQuery();
		if(!result.next()) {
			try {
				if(!org.apache.commons.lang3.StringUtils.isBlank(ISBN.getText()) &&
			    		   !org.apache.commons.lang3.StringUtils.isBlank(title.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(publisher_name.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(publisher_year.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(price.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(book_category.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(quantity.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(threshold.getText())) {
						    	database.setQuery("INSERT INTO order_processing_system.book(ISBN,title,publisher_name,publishing_year,price,"
						    			+ "book_catagory,quantity,threshold) VALUES("
						    			+ "\'" + ISBN.getText() + "\',"
						    			+ "\'" + title.getText() + "\',"
						    			+ "\'" + publisher_name.getText() + "\',"
						    			+ "\'" + publisher_year.getText() + "\',"
						    			+ "\'" + price.getText() + "\',"
						    			+ "\'" + book_category.getText() + "\',"
						    			+ "\'" + quantity.getText() + "\',"
						    			+ "\'" + threshold.getText() + "\'" + ");");
						    	database.executeUpdateQuery();
				}
				else {
					error_msg.setText("Enter all the fields");
					error_msg.setVisible(true);
				}
				database.databaseClose();
			}catch (SQLException e) {
				error_msg.setText(e.getMessage());
				error_msg.setVisible(true);
			}
		}
		else {
			error_msg.setText("There Exist a book with the same ISBN");
			error_msg.setVisible(true);
		}
	}
	@FXML
	void modify() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		database.databaseConnector();
		error_msg.setVisible(false);
		try {
			if(!org.apache.commons.lang3.StringUtils.isBlank(ISBN.getText())) {
				if (!org.apache.commons.lang3.StringUtils.isBlank(title.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET title = " + "\'" + title.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				if (!org.apache.commons.lang3.StringUtils.isBlank(publisher_name.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET publisher_name = " + "\'" + publisher_name.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				if (!org.apache.commons.lang3.StringUtils.isBlank(publisher_year.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET publishing_year = " + "\'" + publisher_year.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				if (!org.apache.commons.lang3.StringUtils.isBlank(price.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET book.price = " + "\'" + price.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				if (!org.apache.commons.lang3.StringUtils.isBlank(book_category.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET book_catagory = " + "\'" + book_category.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				if (!org.apache.commons.lang3.StringUtils.isBlank(quantity.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET quantity = " + "\'" + quantity.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				if (!org.apache.commons.lang3.StringUtils.isBlank(threshold.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET threshold = " + "\'" + threshold.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				if (!org.apache.commons.lang3.StringUtils.isBlank(newISBN.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET ISBN = " + "\'" + newISBN.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				database.databaseClose();
			}
			else {
				error_msg.setText("Specify which book you want to modify!");
				error_msg.setVisible(true);
			}
		}catch (SQLException e) {
			error_msg.setText(e.getMessage());
			error_msg.setVisible(true);
		}
	}
	public Stage getMyStage()
    {
    	return myStage;
    }

    public void  setStage(Stage stage)
    {
    	myStage =stage;
    }
}
