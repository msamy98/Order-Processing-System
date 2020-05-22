package application.allControllers;

/*** Sample Skeleton for 'custmorGui.fxml' Controller Class ***/

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import application.Book;
import application.Database;
import application.Queries;
import application.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class BuyController {

	@FXML // fx:id="error_msg"
    private Label error_msg; // Value injected by FXMLLoader

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
	private Label price; // Value injected by FXMLLoader

	@FXML // fx:id="check_out_btn"
	private Button check_out_btn; // Value injected by FXMLLoader

	private Stage myStage;
	private ObservableList<Book> books_search;
	private ObservableList<Book> cart;
	private Database db;
    private String last_search="";
    private String last_cat="";
    private User user;

	@FXML
	void minus(ActionEvent event) {
		try {
			Integer no = Integer.parseInt(quantity_text.getText());
			if (no > 1) {
				no--;

				int ix = cart_table.getSelectionModel().getSelectedIndex();
				Book book = cart_table.getSelectionModel().getSelectedItem();

				book.setNoOfCopiesInCart(no);
				List<Book> list = new ArrayList<>();
				for (Book b : cart) {
					if (b.getISBN() != book.getISBN()) {
						list.add(b);
					} else {
						list.add(book);
					}
				}
				cart.clear();
				cart.addAll(list);
				cart_table.requestFocus();
				cart_table.getSelectionModel().select(ix);
				cart_table.getFocusModel().focus(ix);
				int tPrice = 0;
				for (Book b : cart) {
					tPrice += b.getPrice() * (b.getNoOfCopiesInCart());
				}
				price.setText(Integer.toString(tPrice));
			} else if (no == 1) {
				delete_from_cart(event);
			}
		} catch (Exception e) {
			error_msg.setText(e.getMessage());
			error_msg.setTextFill(Color.RED);
			error_msg.setVisible(true);
		}
	}

	@FXML
	void plus(ActionEvent event) {
		try {
			Integer no = Integer.parseInt(quantity_text.getText());
			if (no >= 0) {
				no++;
				int ix = cart_table.getSelectionModel().getSelectedIndex();
				Book book = cart_table.getSelectionModel().getSelectedItem();

				book.setNoOfCopiesInCart(no);
				List<Book> list = new ArrayList<>();
				for (Book b : cart) {
					if (b.getISBN() != book.getISBN()) {
						list.add(b);
					} else {
						list.add(book);
					}
				}
				cart.clear();
				cart.addAll(list);
				cart_table.requestFocus();
				cart_table.getSelectionModel().select(ix);
				cart_table.getFocusModel().focus(ix);
				int tPrice = 0;
				for (Book b : cart) {
					tPrice += b.getPrice() * (b.getNoOfCopiesInCart());
				}
				price.setText(Integer.toString(tPrice));
			}
		} catch (Exception e) {
			error_msg.setText(e.getMessage());
			error_msg.setTextFill(Color.RED);
			error_msg.setVisible(true);
		}
	}

	@FXML
	void search_for_books(ActionEvent event) {
		String value = seach_text.getText();
		String cat = search_cat.getValue();
		search_books(value, cat);

	}

	@FXML
	void delete_from_cart(ActionEvent event) {
		try {
		error_msg.setVisible(false);
		int ix = cart_table.getSelectionModel().getSelectedIndex();
		Book book = cart_table.getSelectionModel().getSelectedItem();
		cart.remove(ix);
		if (ix != 0) {

			ix = ix - 1;
		}

		cart_table.requestFocus();
		cart_table.getSelectionModel().select(ix);
		cart_table.getFocusModel().focus(ix);
		int tPrice = 0;
		for (Book b : cart) {
			tPrice += b.getPrice() * (b.getNoOfCopiesInCart());
		}
		price.setText(Integer.toString(tPrice));
		}
		catch(Exception e)
		{
			error_msg.setText("non selcetion to delete");
            error_msg.setVisible(true);
            error_msg.setTextFill(Color.RED);
		}

	}

	@FXML
	void add_book(ActionEvent event) {
		try {
		error_msg.setVisible(false);
		Book book = book_search_table.getSelectionModel().getSelectedItem();
		book.setNoOfCopiesInCart((book.getNoOfCopiesInCart() + 1));
		if (!cart.contains(book)) {
			cart.add(book);
		} else {
			List<Book> list = new ArrayList<>();
			for (Book b : cart) {
				if (b.getISBN() != book.getISBN()) {
					list.add(b);
				} else {
					list.add(book);
				}
			}
			cart.clear();
			cart.addAll(list);
		}
		int tPrice = 0;
		for (Book b : cart) {
			tPrice += b.getPrice() * (b.getNoOfCopiesInCart());
		}
		price.setText(Integer.toString(tPrice));
		}catch (Exception e)
		{
			error_msg.setText("non selcetion to add");
            error_msg.setVisible(true);
            error_msg.setTextFill(Color.RED);
		}
	}

	@FXML
	void check_out_Cart(ActionEvent event) {

		try {
			error_msg.setVisible(false);
			db.databaseConnector();
			Calendar cal = Calendar.getInstance();
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(cal.getTimeInMillis()));
            Queries q = new Queries();
			for (Book b : cart) {
				db.setQuery(q.checkOutCart(b.getISBN(), b.getNoOfCopiesInCart()));
				db.executeUpdateQuery();
				db.setQuery(q.checkOutCart(b.getISBN(),timeStamp,user.getUserName(), b.getNoOfCopiesInCart()));
				db.executeUpdateQuery();
			}

			db.databaseClose();
			cart.clear();
			price.setText("0");
			search_books(last_search, last_cat);



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
			error_msg.setText("no enough in stock");
            error_msg.setVisible(true);
            error_msg.setTextFill(Color.RED);
		}

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
		catList.add("author");
		search_cat.setItems(catList);
		search_cat.setValue("ISBN");
		price.setText("0");

		quantity_text.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					quantity_text.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		cart_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
			@Override
			public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
				if (newValue != null) {
					quantity_text.setText(String.valueOf(newValue.getNoOfCopiesInCart()));
				}
				try {

				} catch (Exception e) {
					// TODO: handle exception
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

		TableColumn authorCol = new TableColumn("Authors");
		authorCol.setCellValueFactory(new PropertyValueFactory("author"));

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
		columns.add(authorCol);
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

	public List<Book> booksFormResultSet(ResultSet resultSet) {

		List<Book> bookList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Book book = new Book();
				book.setISBN(resultSet.getInt("ISBN"));
				book.setTitle(resultSet.getString("title"));
				book.setPublisherName(resultSet.getString("publisher_name"));
				book.setPublishingYear(resultSet.getString("publishing_year"));
				book.setPrice(resultSet.getInt("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setBookCategory(resultSet.getString("catagory_name"));
				book.setAuthor(resultSet.getString("author"));
				book.setNoOfCopiesInCart(0);
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookList;
	}
	private void search_books(String value ,String cat)
	{
		books_search.clear();
		try {
			db.databaseConnector();
			Queries q = new Queries();
			if(cat=="author")
			{
				db.setQuery(q.searchBookQueryAuthor(value));
			}
			else
			{
				db.setQuery(q.searchBookQuery(cat, value));
			}
			ResultSet r = db.executeRetrieveQuery();
			books_search.addAll(booksFormResultSet(r));
			db.databaseClose();
			last_search=value;
			last_cat=cat;
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
	}
	public void setUser(User user)
	{
		error_msg.setVisible(false);
		this.user=user;
	}
}