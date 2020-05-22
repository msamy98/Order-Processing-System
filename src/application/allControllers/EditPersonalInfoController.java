package application.allControllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;
import application.Queries;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditPersonalInfoController {

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
    private Button save_btn;

    @FXML
    private Button quit_btn;
    
    @FXML
    private Label error_msg;

    private Stage myStage;
    private Database database = new Database();
    private User user;

    @FXML
    void quit(ActionEvent event) {
    		
    }

    @FXML
    void save_edits(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	boolean done = false;
    	database.databaseConnector();
    	Queries query = new Queries();
	    database.setQuery(query.modifyPersonalInfoUser(user.getUserName()));
		ResultSet result = database.executeRetrieveQuery();
		if(result.next()) {
		    if (!org.apache.commons.lang3.StringUtils.isBlank(first_name.getText())) {
				database.setQuery(query.modifyPersonalInfoUpdateUser(user.getUserName(),"first_name",first_name.getText()));
				database.executeUpdateQuery();
				done = true;
			}
		    if (!org.apache.commons.lang3.StringUtils.isBlank(last_name.getText())) {
				database.setQuery(query.modifyPersonalInfoUpdateUser(user.getUserName(),"last_name",last_name.getText()));
				database.executeUpdateQuery();
				done = true;
			}
		    if (!org.apache.commons.lang3.StringUtils.isBlank(pass_word.getText())) {
		    	if(pass_word.getText().equals(confirm_pass_word.getText())) {
					database.setQuery(query.modifyPersonalInfoUpdateUser(user.getUserName(),"pass_word",pass_word.getText()));
					database.executeUpdateQuery();
					done = true;
		    	}else {
		    		error_msg.setText("Enter the same Password in the confirm!");
		    		error_msg.setTextFill(Color.RED);
		    		error_msg.setVisible(true);
		    	}
			}
		    if (!org.apache.commons.lang3.StringUtils.isBlank(e_mail.getText())) {
				database.setQuery(query.modifyPersonalInfoUpdateUser(user.getUserName(),"e_mail",e_mail.getText()));
				database.executeUpdateQuery();
				done = true;
			}
		    if (!org.apache.commons.lang3.StringUtils.isBlank(address.getText())) {
				database.setQuery(query.modifyPersonalInfoUpdateUser(user.getUserName(),"shipping_address",address.getText()));
				database.executeUpdateQuery();
				done = true;
			}
		   if (!org.apache.commons.lang3.StringUtils.isBlank(phone.getText())) {
				database.setQuery(query.modifyPersonalInfoUpdateUser(user.getUserName(),"phone",phone.getText()));
				database.executeUpdateQuery();
				done = true;
			}
		    if (!org.apache.commons.lang3.StringUtils.isBlank(user_name.getText())) {
				database.setQuery(query.modifyPersonalInfoUpdateUser(user.getUserName(),"user_name",user_name.getText()));
				database.executeUpdateQuery();
				done = true;
			}
		 }
		database.databaseClose();
		if(done) {
			error_msg.setText("Info Updated");
			error_msg.setTextFill(Color.GREEN);
			error_msg.setVisible(true);
		}else {
			error_msg.setText("Nothing to Update");
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
    	error_msg.setVisible(false);
    	myStage =stage;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
