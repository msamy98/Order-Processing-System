package application.allControllers;

import javafx.scene.control.Label;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private TextField user_name;

    @FXML
    private TextField first_name;

    @FXML
    private TextField last_name;

    @FXML
    private PasswordField pass_word;

    @FXML
    private PasswordField confirm_pass_word;

    @FXML
    private TextField e_mail;

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private Button sign_up_btn;
    
    @FXML
    private Label error_msg;

    private Database database = new Database();
    private Stage myStage;
    @FXML
    void sign_up(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	error_msg.setVisible(false);
    	database.databaseConnector();
    	database.setQuery("SELECT * FROM order_processing_system.users WHERE user_name = " + "\'" + user_name.getText() + "\'" + ";");
    	ResultSet result = database.executeRetrieveQuery();
    	if(!result.next()) {
    		try {
	    		if(!org.apache.commons.lang3.StringUtils.isBlank(user_name.getText()) && 
	    		   !org.apache.commons.lang3.StringUtils.isBlank(first_name.getText())&&
	    				!org.apache.commons.lang3.StringUtils.isBlank(last_name.getText())&&
	    				!org.apache.commons.lang3.StringUtils.isBlank(pass_word.getText())&&
	    				!org.apache.commons.lang3.StringUtils.isBlank(confirm_pass_word.getText())&&
	    				!org.apache.commons.lang3.StringUtils.isBlank(e_mail.getText())&&
	    				!org.apache.commons.lang3.StringUtils.isBlank(address.getText())&&
	    				!org.apache.commons.lang3.StringUtils.isBlank(phone.getText())) {
		    		if (pass_word.getText().equals(confirm_pass_word.getText())) {
				    	database.setQuery("INSERT INTO order_processing_system.users VALUES("
				    			+ "\'" + user_name.getText() + "\',"
				    			+ "\'" + pass_word.getText() + "\',"
				    			+ "\'" + last_name.getText() + "\',"
				    			+ "\'" + first_name.getText() + "\',"
				    			+ "\'" + e_mail.getText() + "\',"
				    			+ "\'" + phone.getText() + "\',"
				    			+ "\'" + address.getText() + "\',"
				    			+ "\'" + "1" + "\'" + ");");
				    	database.executeUpdateQuery();
				    	
				    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignInGui.fxml"));
				    	Parent parentScene;
				    	try {
				    		parentScene = loader.load();
				    		myStage.setTitle("Sign In");
				    		myStage.setScene(new Scene(parentScene, 350.0D, 350.0D));
				    		myStage.setResizable(false);
				    		SignInController signInController =loader.getController();
				    		signInController.setStage(myStage);
				    		myStage.show();
				    		
				    	}catch (IOException e) {
				    		e.printStackTrace();
						}
		    		}
		    		else {
		    			error_msg.setText("enter the same password");
		    			error_msg.setVisible(true);
		    		}
	    		}
	    		else {
	    			error_msg.setText("Fill all the fields");
	    			error_msg.setVisible(true);
	    		}
    		}catch (SQLException e) {
				error_msg.setText(e.getMessage());
				error_msg.setVisible(true);
			}
    		
    	}
    	else {
    		error_msg.setText("this user already exists");
			error_msg.setVisible(true);
    	}
    	database.databaseClose();
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
