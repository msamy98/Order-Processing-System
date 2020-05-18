package application.allControllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import application.Database;
import application.Manger;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private TextField user_name;

    @FXML
    private PasswordField pass_word;

    @FXML
    private Button sign_in_btn;

    @FXML
    private Hyperlink sign_up_link;
    
    @FXML
    private Label error_msg;
    

    private Stage myStage;

    private Database database = new Database();
    @FXML
    void sign_in(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	database.databaseConnector();
    	database.setQuery("SELECT * FROM order_processing_system.users WHERE user_name = " + "\'" + user_name.getText() + "\'" + ";");
    	ResultSet result = database.executeRetrieveQuery();
    	
    	if(result.next() ) {
    		if(!org.apache.commons.lang3.StringUtils.isBlank(user_name.getText())) {
	    		if(pass_word.getText().toString().equals(result.getString("pass_word").toString())) {
	    			
			    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
					Parent root;
					try {
						root = fxmlLoader.load();
						MainController mainController = fxmlLoader.getController();
						if(result.getInt("user_type") == 0) {
		    				Manger manger = new Manger(result);
		    				mainController.setUser(manger);
		    				mainController.porBt.setVisible(true);
		    				mainController.placeBt.setVisible(true);
		    				mainController.modify.setVisible(true);
		    				mainController.confirmBt.setVisible(true);
		    				mainController.reportBt.setVisible(true);
		    			}
		    			else {
		    				User customer = new User(result);
		    				mainController.setUser(customer);
		    				mainController.porBt.setVisible(false);
		    				mainController.placeBt.setVisible(false);
		    				mainController.modify.setVisible(false);
		    				mainController.confirmBt.setVisible(false);
		    				mainController.reportBt.setVisible(false);
		    			}
						
						myStage.setTitle("system");
						myStage.setScene(new Scene(root, 1050.0D, 600.0D));
						myStage.setResizable(false);
						mainController.setStage(myStage);
						myStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		else {
	    			error_msg.setText("Wrong Password");
	    			error_msg.setTextFill(Color.RED);
	    			error_msg.setVisible(true);
	    		}
    		}
    		else {
    			error_msg.setText("Enter User Name!!");
    			error_msg.setTextFill(Color.RED);
    			error_msg.setVisible(true);
    		}
    	}
    	else {
    		error_msg.setText("not valid user");
			error_msg.setTextFill(Color.RED);
			error_msg.setVisible(true);
    	}
    	database.databaseClose();
    }

    @FXML
    void sign_up(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SignUpGui.fxml"));
		Parent root;
		try {
			root = fxmlLoader.load();
			myStage.setTitle("sign up");
			myStage.setScene(new Scene(root, 400.0D, 600.0D));
			myStage.setResizable(false);
			SignUpController signUpController = fxmlLoader.getController();
			signUpController.setStage(myStage);
			myStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    public Stage getMyStage()
    {
    	return myStage;
    }

    public void  setStage(Stage stage)
    {
    	error_msg.setVisible(false);
    	myStage =stage;
    }

}
