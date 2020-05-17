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

	@FXML // fx:id="Archo"
    private AnchorPane Archo; // Value injected by FXMLLoader

    @FXML // fx:id="mainBorderPane"
    private BorderPane mainBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="modify"
    public Button modify; // Value injected by FXMLLoader

    @FXML // fx:id="placeBt"
    public Button placeBt; // Value injected by FXMLLoader

    @FXML // fx:id="bookSearch"
    private Button bookSearch; // Value injected by FXMLLoader

    @FXML // fx:id="editInfoBut"
    private Button editInfoBut; // Value injected by FXMLLoader

    @FXML // fx:id="logOutBt"
    private Button logOutBt; // Value injected by FXMLLoader

    @FXML // fx:id="porBt"
    public Button porBt; // Value injected by FXMLLoader

    
    @FXML // fx:id="placeBt"
    public Button confirmBt;

     private User user;
     private Stage myStage;

     public void setUser(User user) {
    	 this.user = user;
     }
    @FXML
    void Buy_screen(ActionEvent event) {


    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/BuyGui.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		BuyController buyController = loader.getController();
    		buyController.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void edit_info_screen(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/presonalInfoGui.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		BuyController buyController = loader.getController();
    		buyController.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void pro_screen(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Promotion.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		PromotionController controller = loader.getController();
    		controller.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
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
    void place_book_orders(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlaceGui.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		PlaceController placeController = loader.getController();
    		placeController.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void modifyBooks(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifingBooks.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		ModifingBooks modifingBooks = loader.getController();
    		modifingBooks.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    @FXML
    void confirm_book_orders(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConfirmGui.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		ConfirmController conController = loader.getController();
    		conController.setMyStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
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
