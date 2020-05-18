package application.allControllers;

/**
	 * Sample Skeleton for 'custmorGui.fxml' Controller Class
	 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import application.Book;
import application.Database;
import application.Queries;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class BuyController {

	@FXML // fx:id="last_name"
	private Label last_name; // Value injected by FXMLLoader

	@FXML // fx:id="search_btn"
	private Button search_btn; // Value injected by FXMLLoader

	@FXML // fx:id="del_btn"
	private Button del_btn; // Value injected by FXMLLoader

	@FXML // fx:id="search_cat"
	private ComboBox<String> search_cat; // Value injected by FXMLLoader

	@FXML // fx:id="cart_table"
	private TableView<Book> cart_table; // Value injected by FXMLLoader

	@FXML // fx:id="book_search_table"
	private TableView<Book> book_search_table; // Value injected by FXMLLoader

	@FXML // fx:id="minus_btn"
	private Button minus_btn; // Value injected by FXMLLoader

	@FXML // fx:id="quantity_text"
	private TextField quantity_text; // Value injected by FXMLLoader

	@FXML // fx:id="seach_text"
	private TextField seach_text; // Value injected by FXMLLoader

	@FXML // fx:id="plus_btn"
	private Button plus_btn; // Value injected by FXMLLoader

	@FXML // fx:id="first_name"
	private Label first_name; // Value injected by FXMLLoader

	@FXML // fx:id="add_btn"
	private Button add_btn; // Value injected by FXMLLoader

	@FXML // fx:id="price_label"
	private Label price_label; // Value injected by FXMLLoader

	private Stage myStage;
	private ObservableList<Book> books_search;
	private ObservableList<Book> cart;
	private Database db ;

	@FXML
	void minus(ActionEvent event) {
		try {
			Integer no = Integer.getInteger(quantity_text.getText());
			no--;
			if (no >= 0)
				quantity_text.setText(Integer.toString(no));
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	@FXML
	void plus(ActionEvent event) {
		try {
			Integer no = Integer.getInteger(quantity_text.getText());
			no++;
			if (no >= 0)
				quantity_text.setText(Integer.toString(no));
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	@FXML
	void search_for_books(ActionEvent event) {
		String value = seach_text.getText();
		String cat = search_cat.getValue();
		books_search.clear();
		try {
			db.databaseConnector();
			Queries q = new Queries() ;
			db.setQuery(q.searchBookQuery(cat, value));
			ResultSet r = db.executeRetrieveQuery();
			books_search.addAll(booksFormResultSet(r));
			db.databaseClose();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Queries q = new Queries() ;

	}

	@FXML
	void add_book(ActionEvent event) {
		Book book =book_search_table.getSelectionModel().getSelectedItem();
		book.setQuantity(book.getQuantity()+1);
		cart.add(book);
	}

	public Stage getStage() {
		return myStage;
	}

	public void setStage(Stage myStage) {
		this.myStage = myStage;
		db = new Database();

		setupSearchBooksTable();

		puCartTable();
		ObservableList<String> catList = FXCollections.observableArrayList();
		catList.add("ISBN");
		catList.add("title");
		catList.add("publisher_name");
		catList.add("publishing_year");
		catList.add("price");
		catList.add("book_catagory");
		catList.add("quantity");
		search_cat.setItems(catList);
		search_cat.setValue("ISBN");

		quantity_text.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					quantity_text.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

	}

	public void setupSearchBooksTable() {
		// book search table
		books_search = FXCollections.observableArrayList();
		book_search_table.setItems(books_search);
		TableColumn ISBNCol = new TableColumn("ISBN");
		ISBNCol.setCellValueFactory(new PropertyValueFactory("ISBN"));

		TableColumn titleCol = new TableColumn("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory("title"));

		TableColumn publisherCol = new TableColumn("Publisher");
		publisherCol.setCellValueFactory(new PropertyValueFactory("publisherName"));

		TableColumn priceCol = new TableColumn("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory("price"));

		TableColumn catCol = new TableColumn("Book Category");
		catCol.setCellValueFactory(new PropertyValueFactory("bookCategory"));

		TableColumn quantityCol = new TableColumn("Quantity");
		quantityCol.setCellValueFactory(new PropertyValueFactory("quantity"));

		List<TableColumn<Book, String>> columns = new ArrayList<TableColumn<Book, String>>();
		columns.add(ISBNCol);
		columns.add(titleCol);
		columns.add(publisherCol);
		columns.add(priceCol);
		columns.add(catCol);
		columns.add(quantityCol);

		book_search_table.getColumns().setAll(columns);
	}

	public void puCartTable() {
		// cart table
		cart = FXCollections.observableArrayList();
		cart_table.setItems(cart);
		List<TableColumn<Book, String>> columnsCart = new ArrayList<TableColumn<Book, String>>();
		TableColumn ISBNColCart = new TableColumn("ISBN");
		ISBNColCart.setCellValueFactory(new PropertyValueFactory("ISBN"));

		TableColumn titleColCart = new TableColumn("Title");
		titleColCart.setCellValueFactory(new PropertyValueFactory("title"));

		TableColumn publisherColCart = new TableColumn("Publisher");
		publisherColCart.setCellValueFactory(new PropertyValueFactory("publisherName"));

		TableColumn priceColCart = new TableColumn("Price");
		priceColCart.setCellValueFactory(new PropertyValueFactory("price"));

		TableColumn cartCol = new TableColumn("cart");
		cartCol.setCellValueFactory(new PropertyValueFactory("noOfCopiesInCart"));

		columnsCart.add(ISBNColCart);
		columnsCart.add(titleColCart);
		columnsCart.add(priceColCart);
		columnsCart.add(cartCol);

		cart_table.getColumns().setAll(columnsCart);
	}
   public List<Book> booksFormResultSet(ResultSet resultSet)
   {

	   List<Book> bookList = new ArrayList<>();
	   try {
		while(resultSet.next())
		   {
			   Book book = new Book();
			   book.setISBN(resultSet.getInt("ISBN"));
			   book.setTitle(resultSet.getString("title"));
			   book.setPublisherName(resultSet.getString("publisher_name"));
			   book.setPublishingYear(resultSet.getString("publishing_year"));
			   book.setPrice(resultSet.getInt("price"));
			   book.setQuantity(resultSet.getInt("quantity"));
			   book.setBookCategory(resultSet.getString("catagory_name"));
			   book.setNoOfCopiesInCart(0);
			   bookList.add(book);
		   }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

       return bookList;
   }
}
