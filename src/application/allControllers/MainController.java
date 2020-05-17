package application.allControllers;

import java.io.IOException;

import application.FxmlLoader;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainController {

    @FXML
    private AnchorPane Archo;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button logOutBt;

    @FXML
    private Button editInfoBut;

    @FXML
    private Button bookSearch;

    @FXML
    public Button porBt;
    
    @FXML
    public Button modify;
    
    @FXML
    public Button placeBt;
    
     private User user;
     private Stage myStage;
     
     public void setUser(User user) {
    	 this.user = user;
     }
    @FXML
    void Buy_screen(ActionEvent event) {
    	
    	FxmlLoader obj = new FxmlLoader() ; 
    	Pane p = obj.getView(getClass().getResource("/BuyGui.fxml") ); 
    	mainBorderPane.setCenter(p);
    }

    @FXML
    void edit_info_screen(ActionEvent event) {
    	FxmlLoader obj = new FxmlLoader() ; 
    	Pane p = obj.getView(getClass().getResource("/presonalInfoGui.fxml")) ; 
    	mainBorderPane.setCenter(p);
    }

    @FXML
    void pro_screen(ActionEvent event) {
    	/*FxmlLoader obj = new FxmlLoader() ; 
    	Pane p = obj.getView("presonalInfoGui") ; 
    	mainBorderPane.setCenter(p);*/
    }
    @FXML
    void on_logout(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignInGui.fxml"));
    	Parent parent;
    	try {
    		parent = loader.load();
    		myStage.setTitle("Sign In");
    		myStage.setScene(new Scene(parent,350.0D, 350.0D));
    		myStage.setResizable(false);
    		SignInController signInController = loader.getController();
    		signInController.setStage(myStage);
    		myStage.show();
    	}catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    @FXML
    void modifyBooks(ActionEvent event) {
    	FxmlLoader obj = new FxmlLoader() ; 
    	Pane p = obj.getView(getClass().getResource("/ModifingBooks.fxml")) ; 
    	mainBorderPane.setCenter(p);
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
