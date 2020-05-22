package application.allControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ModifingBooks {

	@FXML
	private TextField ISBN;

	@FXML
	private TextField newISBN;

	@FXML
	private TextField title;

	@FXML
	private ComboBox<String> publisher_name;

	@FXML
	private TextField publisher_year;

	@FXML
	private TextField price;

	@FXML
	private ComboBox<String> book_category;

	@FXML
	private TextField quantity;

	@FXML
	private TextField threshold;

	@FXML
	private Label error_msg;
	
    @FXML
    private TextField authorText;

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
			    				!org.apache.commons.lang3.StringUtils.isBlank(publisher_name.getValue())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(publisher_year.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(price.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(quantity.getText())&&
			    				!org.apache.commons.lang3.StringUtils.isBlank(threshold.getText()) && 
			    				!org.apache.commons.lang3.StringUtils.isBlank(authorText.getText()))  {
					
								int category = 1;
								switch(book_category.getValue()) {
									case "Science" : category = 1;
										break;
									case "Art" : category = 2;
										break;
									case "Religion" : category = 3;
										break;
									case "Geography" : category = 5;
										break;
									case "History" : category = 4;
										break;
									default:
										break;
								}
						    	database.setQuery("INSERT INTO order_processing_system.book(ISBN,title,publisher_name,publishing_year,price,"
						    			+ "book_catagory,quantity,threshold) VALUES("
						    			+ "\'" + ISBN.getText() + "\',"
						    			+ "\'" + title.getText() + "\',"
						    			+ "\'" + publisher_name.getValue() + "\',"
						    			+ "\'" + publisher_year.getText() + "\',"
						    			+ "\'" + price.getText() + "\',"
						    			+ "\'" + category + "\',"
						    			+ "\'" + quantity.getText() + "\',"
						    			+ "\'" + threshold.getText() + "\'" + ");");
						    	database.executeUpdateQuery();
						    	
						    	String s = authorText.getText() ; 
						    	s.replaceAll("\\s+","");
						    	String[] arrOfStr = s.split(",");
						    	for ( int i = 0 ; i < arrOfStr.length ; i++) {
						    		database.setQuery("insert into authortable value (  "+ ISBN.getText() +" , \'"+ arrOfStr[i]  +"\' );");
						    		database.executeUpdateQuery();
						    	}
				}
				else {
					error_msg.setText("Enter all the fields");
					error_msg.setTextFill(Color.RED);
					error_msg.setVisible(true);
				}
				database.databaseClose();
				error_msg.setText("Book Added SUCCESSFULLY!!");
				error_msg.setTextFill(Color.GREEN);
				error_msg.setVisible(true);
			}catch (SQLException e) {
				error_msg.setText(e.getMessage());
				error_msg.setTextFill(Color.RED);
				error_msg.setVisible(true);
				System.out.println(e.getMessage());
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
				if (!org.apache.commons.lang3.StringUtils.isBlank(publisher_name.getValue())) {
					database.setQuery("UPDATE order_processing_system.book SET publisher_name = " + "\'" + publisher_name.getValue() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
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
				if (!org.apache.commons.lang3.StringUtils.isBlank(book_category.getValue())) {
					int category = 1;
					switch(book_category.getValue()) {
						case "Science" : category = 1;
							break;
						case "Art" : category = 2;
							break;
						case "Religion" : category = 3;
							break;
						case "Geography" : category = 5;
							break;
						case "History" : category = 4;
							break;
						default:
							break;
					}
					database.setQuery("UPDATE order_processing_system.book SET book_catagory = " + "\'" + category + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
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
				if (!org.apache.commons.lang3.StringUtils.isBlank(authorText.getText())) {
					String s = authorText.getText() ; 
			    	s.replaceAll("\\s+","");
			    	String[] arrOfStr = s.split(",");
			    	database.setQuery("delete from order_processing_system.authortable where isbn = "+ ISBN.getText() +" ; ");
			    	database.executeUpdateQuery();
			    	for ( int i = 0 ; i < arrOfStr.length ; i++) {
			    		database.setQuery("insert into authortable value (  "+ ISBN.getText() +" , \'"+ arrOfStr[i]  +"\' );");
			    		database.executeUpdateQuery();
			    	}
				}
				if (!org.apache.commons.lang3.StringUtils.isBlank(newISBN.getText())) {
					database.setQuery("UPDATE order_processing_system.book SET ISBN = " + "\'" + newISBN.getText() + "\'" + "WHERE (ISBN = " + "\'" + ISBN.getText() + "\');");
					database.executeUpdateQuery();
				}
				database.databaseClose();
				error_msg.setText("Book Modified SUCCESSFULLY!!");
				error_msg.setTextFill(Color.GREEN);
				error_msg.setVisible(true);
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

    public void  setStage(Stage stage) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
    	myStage =stage;
    	ObservableList<String> catList = FXCollections.observableArrayList();
		catList.add("Art");
		catList.add("Geography");
		catList.add("History");
		catList.add("Religion");
		catList.add("Science");
		book_category.setItems(catList);
		book_category.setValue("Art");
		
		ObservableList<String> pubList = FXCollections.observableArrayList();
		database.databaseConnector();
		database.setQuery("SELECT * FROM order_processing_system.publisher;");
		ResultSet result = database.executeRetrieveQuery();
		String firstValue = null;
		boolean stored = false;
		while(result.next()) {
			if(!stored)
				firstValue = result.getString("PUBLISHER_NAME");
			pubList.add(result.getString("PUBLISHER_NAME"));
			stored = true;
		}
		publisher_name.setValue(firstValue);
		publisher_name.setItems(pubList);
		
    }
}





















