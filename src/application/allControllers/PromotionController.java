package application.allControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import application.Database;
import application.Queries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class PromotionController {



    @FXML
    private TextField userSearchText;

    @FXML
    private Button PromoteBt;

    @FXML
    private Button userNameSearchBt;


    @FXML
    private TableView<?> userTable;


    private Database db ;

    private Stage myStage;




    @FXML
    void searchInUser(ActionEvent event) {
    	if (userSearchText.getText() != "") {
    		try {
    			db.databaseConnector();
				Queries q = new Queries() ;
				String s = q.promotionSearchQuery(userSearchText.getText()) ;
				db.setQuery(s) ;
				ResultSet r = db.executeRetrieveQuery() ;




			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    }

    @FXML
    void udateUserStatus(ActionEvent event) {
    	/* get selected inbn */
    	String userName = "" ;
    	if (userName != "" ) {
    		try {
				db.databaseConnector();
				Queries q = new Queries() ;
				db.setQuery(q.promtionUpdateQuery(userName));
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
    	db = new Database() ;
    }

}
