package application.allControllers;

import java.io.IOException;
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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    
    @FXML
    private Label error_msg;

    private Database database ;

    private Stage myStage;

    private ObservableList<Book_order> placeList;



    @FXML
    void doingSearch(ActionEvent event) {

    	if (!org.apache.commons.lang3.StringUtils.isBlank(placeSearchText.getText())) {
    		try {
				database.databaseConnector();
				tableViewing(placeSearchText.getText());
				TableOfOrder.refresh();
				database.databaseClose();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}catch ( SQLException e) {
				error_msg.setText(e.getMessage());
				error_msg.setTextFill(Color.RED);
				error_msg.setVisible(true);
			}
    	}
    	else {
    		error_msg.setText("Enter Title first!!");
			error_msg.setTextFill(Color.RED);
			error_msg.setVisible(true);
    	}
    }

    @FXML
    void updatePlace(ActionEvent event) {
    	error_msg.setVisible(false);
    	TableViewSelectionModel<Book_order> selectedRow = TableOfOrder.getSelectionModel();
    	selectedRow.setSelectionMode(SelectionMode.SINGLE);
    	ObservableList<Book_order> selectedItems = selectedRow.getSelectedItems();
    	String ISBN = "";
    	if(selectedItems.size()>0) {
    		Integer isbn = selectedItems.get(0).getISBN();
	    	ISBN = isbn.toString() ;
    	}else {
	    	ISBN = "";
	    	error_msg.setText("Nothing Selected!!");
			error_msg.setTextFill(Color.RED);
			error_msg.setVisible(true);
    	}
    	if (ISBN != "" ) {
    		try {
				database.databaseConnector();
				Queries q = new Queries() ;
				database.setQuery(q.PlaceUpdateQuery(ISBN));
				
				database.executeUpdateQuery();

				tableViewing(null);
				TableOfOrder.refresh();
				database.databaseClose();
				error_msg.setText("Order Placed!!");
				error_msg.setTextFill(Color.GREEN);
				error_msg.setVisible(true);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    public Stage getMyStage()
    {
    	return myStage;
    }

    public void  setStage(Stage stage) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException 
    {
    	error_msg.setVisible(false);
    	this.myStage =stage;
    	database = new Database() ;
    	database.databaseConnector();
    	tableViewing(null);
    	database.databaseClose();
    }
    
    private void tableViewing(String title) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	
    	
    	Queries query = new Queries();
    	
    	database.setQuery(query.placeSelectJoinBook(title));
    	ResultSet bookResult = database.executeRetrieveQuery();
    	
    	Book_order order = new Book_order();
    	placeList = FXCollections.observableArrayList();
    	
    	while(bookResult.next()) {
    		order.setISBN(bookResult.getInt("ISBN"));
    		order.setDate(bookResult.getString("order_date"));
    		order.setOrederId(bookResult.getInt("order_id"));
	    	order.setPrice(bookResult.getInt("price"));
	    	order.setPublisherName(bookResult.getString("publisher_name"));
	    	order.setQuantity(bookResult.getInt("quantity"));
	    	order.setTitle(bookResult.getString("title"));
    		
	    	placeList.add(order);
	    	order = new Book_order();
    	}
    	TableOfOrder.setItems(placeList);
    	
    	
        List<TableColumn<Book_order,String>> placeColumns =new ArrayList<TableColumn<Book_order,String>>();
        
        TableColumn ISBNColCart = new TableColumn("ISBN");
        ISBNColCart.setCellValueFactory(new PropertyValueFactory("ISBN"));

		TableColumn titleColCart = new TableColumn("Title");
		titleColCart.setCellValueFactory(new PropertyValueFactory("title"));

        TableColumn publisherColCart = new TableColumn("Publisher");
        publisherColCart.setCellValueFactory(new PropertyValueFactory("publisherName"));


        TableColumn dateColCart = new TableColumn("Date");
        dateColCart.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn quantity = new TableColumn("quantity");
        quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory("price"));
        
        TableColumn orderId = new TableColumn("Order Id");
        orderId.setCellValueFactory(new PropertyValueFactory("orderId"));




        placeColumns.add(ISBNColCart);
        placeColumns.add(titleColCart);
        placeColumns.add(publisherColCart);
        placeColumns.add(dateColCart);
        placeColumns.add(quantity);
        placeColumns.add(price);
        placeColumns.add(orderId);

        TableOfOrder.getColumns().setAll(placeColumns);
        
    }

}