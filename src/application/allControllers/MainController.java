package application.allControllers;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import application.Database;
import application.FxmlLoader;
import application.Queries;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;



public class MainController {

	@FXML // fx:id="Archo"
    private AnchorPane Archo; // Value injected by FXMLLoader

    @FXML // fx:id="mainBorderPane"
    private BorderPane mainBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="modify"
    public Button modify; // Value injected by FXMLLoader

    @FXML // fx:id="placeBt"
    public Button placeBt; // Value injected by FXMLLoader

    @FXML // fx:id="bookSearch"
    private Button bookSearch; // Value injected by FXMLLoader

    @FXML // fx:id="editInfoBut"
    private Button editInfoBut; // Value injected by FXMLLoader

    @FXML // fx:id="logOutBt"
    private Button logOutBt; // Value injected by FXMLLoader

    @FXML // fx:id="porBt"
    public Button porBt; // Value injected by FXMLLoader


    @FXML // fx:id="placeBt"
    public Button confirmBt;

    @FXML
    public Button reportBt;

    @FXML
    public Label label1;

    @FXML
    private Label label2;

     private User user;

     private Stage myStage;

     public void setUser(User user) {
    	 this.user = user;
     }
    @FXML
    void Buy_screen(ActionEvent event) {


    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/BuyGui.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		BuyController buyController = loader.getController();
    		buyController.setStage(myStage);
    		buyController.setUser(user);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void edit_info_screen(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/presonalInfoGui.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    	    EditPersonalInfoController controller = loader.getController();
    	    controller.setUser(user);
    		controller.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void pro_screen(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Promotion.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		PromotionController controller = loader.getController();
    		controller.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML
    void on_logout(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignInGui.fxml"));
    	Parent parent;
    	try {
    		parent = loader.load();
    		myStage.setTitle("Sign In");
    		myStage.setScene(new Scene(parent,350.0D, 350.0D));
    		myStage.setResizable(false);
    		SignInController signInController = loader.getController();
    		signInController.setStage(myStage);
    		myStage.show();
    	}catch (IOException e) {
			e.printStackTrace();
		}

    }
    @FXML
    void place_book_orders(ActionEvent event)  {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlaceGui.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		PlaceController placeController = loader.getController();
    		placeController.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void modifyBooks(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifingBooks.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		ModifingBooks modifingBooks = loader.getController();
    		modifingBooks.setStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }


    @FXML
    void confirm_book_orders(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConfirmGui.fxml"));
    	Pane p ;
    	try {
    		p = loader.load();
    		ConfirmController conController = loader.getController();
    		conController.setMyStage(myStage);
    		mainBorderPane.setCenter(p);
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void makeRebort(ActionEvent event) {

    	Queries q = new Queries();
    	Database db = new Database() ;
    	try {
			db.databaseConnector();
			db.setQuery(q.TopSelling());
	    	ResultSet r = db.executeRetrieveQuery();

	    	String ans = "" ;
	    	String num1 = ""  ;
	    	while(r.next()) {

	    		ans += r.getString("title") + " \r\n" ;
	    		num1 += r.getString("number_of_sold") + " \r\n";
	    	}

	    	db.setQuery(q.Topcustomer());
	    	ResultSet r1 = db.executeRetrieveQuery();
	    	String ans2 = "" ;
	    	String num2 = "" ;
 	    	while(r1.next()) {

	    		ans2 += r1.getString("userName") + " \r\n"  ;
	    		num2 += r1.getString("number_of_books") + " \r\n";
	    	}

 	    	db.setQuery(q.totalSalesBooks());
	    	ResultSet r2 = db.executeRetrieveQuery();
	    	String isbn = "" ;
 	    	String name = "" ;
 	    	String sales = "";
 	    	while(r2.next()) {
 	    		isbn += r2.getString("isbn") + " \r\n"   ;
 	    		name += r2.getString("title") +" \r\n"   ;
 	    		sales += r2.getString("total_sales") + " \r\n"   ;

 	    	}


	    	JasperReport j;
			try {
				String filePath = new File("").getAbsolutePath();
				//filePath  += "\\src" ;
				System.out.println(filePath);
				j = JasperCompileManager.compileReport( filePath + "\\Report.jrxml" );
				JRDataSource d = new JREmptyDataSource();
				Map<String , Object> m = new HashMap<String, Object>() ;
				m.put("el7amd", ans2) ;
				m.put("pnum1", num2);
				m.put("pnum2", num1);
				m.put("secPar", ans) ;
				m.put("Pnum3", sales) ;
				m.put("thirPar", name);
				m.put("isbnp", isbn);
				JasperPrint jp = JasperFillManager.fillReport( j, m , d);
				JasperExportManager.exportReportToPdfFile(jp , filePath + "\\report.pdf");

			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	System.out.println("done");
	    	db.databaseClose();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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
