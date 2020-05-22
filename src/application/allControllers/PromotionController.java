package application.allControllers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.Database;
import application.Queries;
import application.User;
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
public class PromotionController {



    @FXML
    private TextField userSearchText;

    @FXML
    private Button PromoteBt;

    @FXML
    private Button userNameSearchBt;

    @FXML
    private TableView<User> userTable;

    private Database db ;

    private Stage myStage;

    private  ObservableList<User> proList;




    @FXML
    void searchInUser(ActionEvent event) {
    	if (userSearchText.getText() != "") {
    		try {
    			db.databaseConnector();
				Queries q = new Queries() ;
				String s = q.promotionSearchQuery(userSearchText.getText()) ;
				//System.out.println(s);
				db.setQuery(s) ;
				ResultSet userData = db.executeRetrieveQuery() ;
				ListOfUser(proList , userData) ;
				db.databaseClose();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    }

    @FXML
    void udateUserStatus(ActionEvent event) {
    	/* get selected inbn */
    	int ix = userTable.getSelectionModel().getSelectedIndex();
    	User u = userTable.getSelectionModel().getSelectedItem();
    	String userName = u.getUserName() ;
    	if (userName != "" ) {
    		try {
				db.databaseConnector();
				Queries q = new Queries() ;
				db.setQuery(q.promtionUpdateQuery(userName));
				int ok = db.executeUpdateQuery() ;
				if (ok != 0 ) {
					proList.remove(ix);
					System.out.println("done");
				}else {
					System.out.println("error");
				}
				db.databaseClose();
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
    	//user_name , first_name , last_name , e_mail , shipping_address

    	proList = FXCollections.observableArrayList();
    	userTable.setItems(proList);

        List<TableColumn<User,String>> columnsCart =new ArrayList<TableColumn<User,String>>();
        TableColumn user_name = new TableColumn("user_name");
        user_name.setCellValueFactory(new PropertyValueFactory("userName"));

		TableColumn first_name = new TableColumn("first_name");
		first_name.setCellValueFactory(new PropertyValueFactory("firstName"));

        TableColumn last_name = new TableColumn("last_name");
        last_name.setCellValueFactory(new PropertyValueFactory("lastName"));


        TableColumn e_mail = new TableColumn("e_mail");
        e_mail.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn shipping_address = new TableColumn("shipping_address");
        shipping_address.setCellValueFactory(new PropertyValueFactory("ShippingAddress"));




        columnsCart.add(user_name);
        columnsCart.add(first_name);
        columnsCart.add(last_name) ;
        columnsCart.add(e_mail);
        columnsCart.add(shipping_address);

        userTable.getColumns().setAll(columnsCart);

        try {
			db.databaseConnector();
			Queries q = new Queries() ;
			String s = q.promotionSearchQuery(null) ;
			//System.out.println(s);
			db.setQuery(s) ;
			ResultSet userData = db.executeRetrieveQuery() ;
			ListOfUser(proList , userData) ;
			db.databaseClose();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void ListOfUser(ObservableList<User> proList , ResultSet userData  ) throws SQLException {

    	proList.clear();
        ResultSetMetaData metaData = userData.getMetaData();
        while(userData.next()) {
        	User u = new User(userData) ;
            for(int i=1 ; i<=metaData.getColumnCount() ; i++) {
                switch (i) {
                case 1: //System.out.println(userData.getString(i));
                	u.setUserName(userData.getString(i)) ;
                    break;
                case 2: u.setFirstName( userData.getString(i));
                    break;
                case 3: u.setLastName(userData.getString(i));
                    break;
                case 4: u.setEmail(userData.getString(i));
                    break;
                case 5: u.setShippingAddress(userData.getString(i));
                    break;
                default:
                    break;
                }
            }
            proList.add(u) ;
        }


    }

    /* private void ListOfUser(ObservableList<User> proList , ResultSet userData  ) throws SQLException {


        ResultSetMetaData metaData = userData.getMetaData();
        while(userData.next()) {
        	User u = new User() ;
            for(int i=1 ; i<=metaData.getColumnCount() ; i++) {
                switch (i) {
                case 1: //System.out.println(userData.getString(i));
                	u.setUserName(userData.getString(i)) ;
                    break;
                case 2: u.setPassword(userData.getString(i));
                    break;
                case 3: u.setFirstName( userData.getString(i));
                    break;
                case 4: u.setLastName(userData.getString(i));
                    break;
                case 5: u.setEmail(userData.getString(i));
                    break;
                case 6: u.setPhone(userData.getString(i));
                    break;
                case 7: u.setShippingAddress(userData.getString(i));
                    break;
                case 8: u.setUserType(userData.getInt(i));
                    break;
                default:
                    break;
                }
            }
            proList.add(u) ;
        }


    }
    */



}
