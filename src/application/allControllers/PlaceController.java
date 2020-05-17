package application.allControllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;
import application.Queries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlaceController {

    @FXML
    private TableView<?> TableOfOrder;

    @FXML
    private Button placeBt;

    @FXML
    private TextField placeSearchText;

    @FXML
    private Button searchBt;

    private Database db ;

    private Stage myStage;

    public PlaceController() {
		// TODO Auto-generated constructor stub
    	db = new Database() ;
	}

    @FXML
    void doingSearch(ActionEvent event) {

    	if (placeSearchText.getText() != "" ) {
    		try {
				db.databaseConnector();
				Queries q = new Queries() ;
				db.setQuery(q.PlaceSearchQuery(placeSearchText.getText()));
				ResultSet r = db.executeRetrieveQuery();


			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void updatePlace(ActionEvent event) {
    	/* get selected insb */
    	String isbn = "" ;
    	if (isbn != "" ) {
    		try {
				db.databaseConnector();
				Queries q = new Queries() ;
				db.setQuery(q.PlaceUpdateQuery(isbn));
				int ok = db.executeUpdateQuery() ;
				if (ok != 0 )
					System.out.println("done");
				else {
					System.out.println("error");
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
