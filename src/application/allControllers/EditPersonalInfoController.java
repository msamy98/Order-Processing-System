package application.allControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    private Stage myStage;

    @FXML
    void quit(ActionEvent event) {

    }

    @FXML
    void save_edits(ActionEvent event) {

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
