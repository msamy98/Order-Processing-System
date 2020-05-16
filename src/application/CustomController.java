package application;


/**
	 * Sample Skeleton for 'custmorGui.fxml' Controller Class
	 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;


	public class CustomController  {

	    @FXML // fx:id="log_out_btn"
	    private Button log_out_btn; // Value injected by FXMLLoader

	    @FXML // fx:id="last_name"
	    private Label last_name; // Value injected by FXMLLoader

	    @FXML // fx:id="search_btn"
	    private Button search_btn; // Value injected by FXMLLoader

	    @FXML // fx:id="del_btn"
	    private Button del_btn; // Value injected by FXMLLoader

	    @FXML // fx:id="edit_info_btn"
	    private Button edit_info_btn; // Value injected by FXMLLoader

	    @FXML // fx:id="search_cat"
	    private ComboBox<?> search_cat; // Value injected by FXMLLoader

	    @FXML // fx:id="cart_table"
	    private TableView<?> cart_table; // Value injected by FXMLLoader

	    @FXML // fx:id="book_search_table"
	    private TableView<?> book_search_table; // Value injected by FXMLLoader

	    @FXML // fx:id="minus_btn"
	    private Button minus_btn; // Value injected by FXMLLoader

	    @FXML // fx:id="quantity_text"
	    private TextField quantity_text; // Value injected by FXMLLoader

	    @FXML // fx:id="seach_text"
	    private TextField seach_text; // Value injected by FXMLLoader

	    @FXML // fx:id="plus_btn"
	    private Button plus_btn; // Value injected by FXMLLoader

	    @FXML // fx:id="first_name"
	    private Label first_name; // Value injected by FXMLLoader

	    @FXML
	    void minus(ActionEvent event) {

	    }

	    @FXML
	    void plus(ActionEvent event) {

	    }

	    @FXML
	    void search_for_books(ActionEvent event) {

	    }

	    @FXML
	    void edit_personal_info(ActionEvent event) {

	    }

	    @FXML
	    void log_out(ActionEvent event) {

	    }

}
