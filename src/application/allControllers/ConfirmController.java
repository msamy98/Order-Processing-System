package application.allControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Book_order;
import application.Database;
import application.Queries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ConfirmController {

    @FXML
    private TableView<Book_order> TableOfOrder;

    @FXML
    private Button ConfirmBt;

    @FXML
    private TextField SearchText;

    @FXML
    private Button searchBt;
    
    private Database db ; 
    
    private Stage myStage;
    
    private ObservableList<Book_order> confirmList;
    
 
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

	public Stage getMyStage() {
		return myStage;
	}

	public void setMyStage(Stage myStage) {
		
		this.myStage = myStage;
		db = new Database() ;
		
		confirmList = FXCollections.observableArrayList();
    	TableOfOrder.setItems(confirmList);
    	
        List<TableColumn<Book_order,String>> columnsCart =new ArrayList<TableColumn<Book_order,String>>();
        TableColumn ISBNColCart = new TableColumn("ISBN");
        ISBNColCart.setCellValueFactory(new PropertyValueFactory("ISBN"));

		TableColumn titleColCart = new TableColumn("Title");
		titleColCart.setCellValueFactory(new PropertyValueFactory("title"));

        TableColumn publisherColCart = new TableColumn("Publisher");
        publisherColCart.setCellValueFactory(new PropertyValueFactory("publisherName"));
        
      
        TableColumn priceColCart = new TableColumn("Price");
        priceColCart.setCellValueFactory(new PropertyValueFactory("price"));
        
        TableColumn quan = new TableColumn("quantity");
        quan.setCellValueFactory(new PropertyValueFactory("quantity"));



       
        columnsCart.add(ISBNColCart);
        columnsCart.add(titleColCart);
        columnsCart.add(priceColCart);
        columnsCart.add(priceColCart);
        columnsCart.add(quan);

        TableOfOrder.getColumns().setAll(columnsCart);
		
	}

}
