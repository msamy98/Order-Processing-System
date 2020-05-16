package application;


import javafx.event.ActionEvent;

/**
 * Sample Skeleton for 'SignInGui.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController {

    @FXML // fx:id="user_name"
    private TextField user_name; // Value injected by FXMLLoader

    @FXML // fx:id="pass_word"
    private PasswordField pass_word; // Value injected by FXMLLoader

    @FXML // fx:id="sign_in_btn"
    private Button sign_in_btn; // Value injected by FXMLLoader

    @FXML // fx:id="sign_up_link"
    private Hyperlink sign_up_link; // Value injected by FXMLLoader

    @FXML
    void sign_in(ActionEvent event) {

    }

    @FXML
    void sign_up(ActionEvent event) {

    }

}
