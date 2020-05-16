package application.allControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void sign_up(ActionEvent event) {

    }

}
