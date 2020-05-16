package application;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader {
	
	
	private Pane view ; 
	
	
	public Pane getView (String name ) {
		
		
		URL url = Main.class.getResource("/application/" + name + ".fxml");
		System.out.println(url.toString());
		if (url == null)
			System.out.println("EEEE");
		else {
			
			try {
				view = new FXMLLoader().load(url) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("in catch");
				e.printStackTrace();
			} 
		}
		
		
		return view;
	}

}
