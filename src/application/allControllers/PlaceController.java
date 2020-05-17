package application.allControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Book;
import application.Book_order;
import application.Database;
import application.Queries;
import application.Sale;
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

public class PlaceController {

    @FXML
    private TableView<Book_order> TableOfOrder;

    @FXML
    private Button placeBt;

    @FXML
    private TextField placeSearchText;

    @FXML
    private Button searchBt;

    private Database db ;

    private Stage myStage;

    private ObservableList<Book_order> placeList;



    @FXML
    void doingSearch(ActionEvent event) {

    	//System.out.println(placeSearchText.getText());

    	if ( !placeSearchText.getText().trim().isEmpty()  && placeSearchText.getText() != null) {
    		try {
    			System.out.println("ssssssssssssss");
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
    	 //isbn , title , publisher_name , price , quan
    	this.myStage =stage;

    	db = new Database() ;

    	placeList = FXCollections.observableArrayList();
    	TableOfOrder.setItems(placeList);

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
        columnsCart.add(publisherColCart);
        columnsCart.add(priceColCart);
        columnsCart.add(quan);

        TableOfOrder.getColumns().setAll(columnsCart);
    }
}
