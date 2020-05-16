package application;
	
import java.sql.*;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println(getClass().getResource("MainGui.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("MainGui.fxml")) ; 
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Database bookstore = new Database() ; 
			bookstore.databaseConnector(); 
			//bookstore.setQuery("INSERT INTO order_processing_system.publisher VALUES(\'Ahmed\', \'Zecola\', \'012-27-11-14-2\');");
			//bookstore.excuteUpdateQuery();
			bookstore.setQuery("SELECT * FROM order_processing_system.publisher;");
			ResultSet result = bookstore.executeRetriveQuery();
			ResultSetMetaData metaData = result.getMetaData();
			while(result.next()) {
				StringBuilder temp = new StringBuilder();
				for (int i = 1 ; i<=metaData.getColumnCount() ; i++) {
					if (i>1) {
						temp.append(" , ");
					}
					temp.append("\"");
					temp.append(result.getString(i));
					temp.append("\"");
				}
				System.out.println(temp.toString());
			}
			result.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
