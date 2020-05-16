package application.allControllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    private Stage myStage;

    @FXML
    void sign_in(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
		Parent root;
		try {
			root = fxmlLoader.load();
			myStage.setTitle("system");
			myStage.setScene(new Scene(root, 879.0D, 564.0D));
			myStage.setResizable(false);
			//SignUpController signUpController = fxmlLoader.getController();
			myStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void sign_up(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SignUpGui.fxml"));
		Parent root;
		try {
			root = fxmlLoader.load();
			myStage.setTitle("sign up");
			myStage.setScene(new Scene(root, 879.0D, 564.0D));
			myStage.setResizable(false);
			//SignUpController signUpController = fxmlLoader.getController();
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
    	myStage =stage;
    }

}
