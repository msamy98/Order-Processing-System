package application.allControllers;

import application.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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
    private Button porBt;

    @FXML
    void Buy_screen(ActionEvent event) {
    	FxmlLoader obj = new FxmlLoader() ; 
    	Pane p = obj.getView("BuyGui") ; 
    	mainBorderPane.setCenter(p);
    }

    @FXML
    void edit_info_screen(ActionEvent event) {
    	FxmlLoader obj = new FxmlLoader() ; 
    	Pane p = obj.getView("presonalInfoGui") ; 
    	mainBorderPane.setCenter(p);
    }

    @FXML
    void pro_screen(ActionEvent event) {
    	/*FxmlLoader obj = new FxmlLoader() ; 
    	Pane p = obj.getView("presonalInfoGui") ; 
    	mainBorderPane.setCenter(p);*/
    }

}
