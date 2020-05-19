package application;

import java.sql.*;
import application.allControllers.SignInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//System.out.println(getClass().getResource("MainScreen.fxml"));
			FXMLLoader fxmlLoader = new FXMLLoader();
	    	fxmlLoader.setLocation(getClass().getResource("/SignInGui.fxml")) ;
	    	Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			SignInController signInController =fxmlLoader.getController();
			signInController.setStage(primaryStage);
			primaryStage.show();



			Database bookstore = new Database() ;
			bookstore.databaseConnector();


			//bookstore.setQuery("INSERT INTO order_processing_system.publisher VALUES(\'Ziad Taha\', \'Ard El Abtal\', \'011-5537-6877\');");
			//bookstore.excuteUpdateQuery();
			//bookstore.setQuery("DELETE FROM order_processing_system.publisher WHERE PUBLISHER_NAME = \'Ziad Taha\';");
			//bookstore.excuteUpdateQuery();
			bookstore.setQuery("SELECT * FROM order_processing_system.publisher;");
			ResultSet result = bookstore.executeRetrieveQuery();
			ResultSetMetaData metaData = result.getMetaData();

			while(result.next()) {
				StringBuilder temp = new StringBuilder();

				for (int i = 1 ; i<=metaData.getColumnCount() ; i++) {
					if (i>1) {
						temp.append("\n");
					}
					temp.append(metaData.getColumnName(i)).append(": ");
					temp.append("\"");
					temp.append(result.getString(i));
					temp.append("\"");
				}
				System.out.println(temp.toString());
				System.out.println("-----------------------------");
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