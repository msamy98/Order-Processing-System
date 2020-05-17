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

public class ConfirmController {

    @FXML
    private TableView<?> TableOfOrder;

    @FXML
    private Button ConfirmBt;

    @FXML
    private TextField SearchText;

    @FXML
    private Button searchBt;
    
    private Database db ; 
    
    public ConfirmController() {
    	db = new Database() ; 
    	// TODO Auto-generated constructor stub
	}

    @FXML
    void doingSearch(ActionEvent event) {
    	
    	if (SearchText.getText() != "" ) {
    		try {
				db.databaseConnector();
				Queries q = new Queries() ; 
				db.setQuery(q.ConfirmSearchQuery(SearchText.getText()));
				ResultSet r = db.executeRetrieveQuery();
				
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }


    @FXML
    void updateConfirm(ActionEvent event) {
    	/* get selected insb */ 
    	String isbn = "" ; 
    	if (isbn != "" ) {
    		try {
				db.databaseConnector();
				Queries q = new Queries() ; 
				db.setQuery(q.ConfirmUpdateQuert(isbn));
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

}
