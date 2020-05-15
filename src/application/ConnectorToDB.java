package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectorToDB {
	
	static Connection connection = null ;
	static String databaseName = "" ; 
	static String url = "jdbc:mysql://localhost/" + databaseName ;
	static String userName = "manger" ; 
	static String pas = "123456mng" ; 
	
	
	public void conToDp () {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url , userName , pas);
			PreparedStatement ps = connection.prepareStatement("insert into order_processing_system.publisher values('zoooom','124Th behind chair factory','2124-5676-8798');") ; 
			int status = ps.executeUpdate(); 
			if ( status != 0 ) {
		
				System.out.println("done");
				
			}else {
				
				System.out.println("error");
			}
			
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
}
