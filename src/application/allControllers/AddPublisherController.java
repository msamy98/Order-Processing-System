package application.allControllers;


import java.sql.ResultSet;
import java.sql.SQLException;
import application.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class AddPublisherController {
	
	@FXML
	private TextField publisher_name;
	
	@FXML
	private TextField address;
	
	@FXML
	private TextField phone;
	
	@FXML
	private Button addNew;
	
	@FXML
	private Label error_msg;
	
	private Stage myStage;
	private Database database = new Database();

	@FXML
	void addNewPublisher(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		error_msg.setVisible(false);
		database.databaseConnector();
		database.setQuery("SELECT * FROM order_processing_system.publisher WHERE PUBLISHER_NAME = " + "\'" + publisher_name.getText() + "\'" + ";");
		ResultSet result = database.executeRetrieveQuery();
		if(!result.next()) {
			try {
				if(!org.apache.commons.lang3.StringUtils.isBlank(publisher_name.getText()) &&
			       !org.apache.commons.lang3.StringUtils.isBlank(address.getText())&&
			       !org.apache.commons.lang3.StringUtils.isBlank(phone.getText())) {
						    	database.setQuery("INSERT INTO order_processing_system.publisher(PUBLISHER_NAME,ADDRESS,TEL) VALUES("
						    			+ "\'" + publisher_name.getText() + "\',"
						    			+ "\'" + address.getText() + "\',"
						    			+ "\'" + phone.getText() + "\'" + ");");
						    	database.executeUpdateQuery();
						    	error_msg.setText("Publisher is added SUCCESSFULLY !!");
								error_msg.setTextFill(Color.GREEN);
								error_msg.setVisible(true);
				}
				else {
					error_msg.setText("Enter all the fields");
					error_msg.setTextFill(Color.RED);
					error_msg.setVisible(true);
				}
				database.databaseClose();
			}catch (SQLException e) {
				error_msg.setText(e.getMessage());
				error_msg.setTextFill(Color.RED);
				error_msg.setVisible(true);
			}
		}
		else {
			error_msg.setText("There Exist a Publisher with the same Name");
			error_msg.setTextFill(Color.RED);
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
