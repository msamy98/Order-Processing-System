package application;

import java.sql.*;



public class Database {
	
	static Connection connection = null ;
	static String databaseName = "" ; 
	static String url = "jdbc:mysql://localhost/" + databaseName + "?autoReconnect=true&useSSL=false";
	static String userName = "manger" ; 
	static String password = "123456mng" ; 
	private String query = "";
	
	

	public void databaseConnector () throws InstantiationException,IllegalAccessException,ClassNotFoundException,SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url , userName , password);
	}
	
	public void databaseClose() throws SQLException{
		if(connection != null) {
			connection.close();
		}
	}
	/***to execute the update queries as update and delete returning the status of the operation***/
	public int executeUpdateQuery() throws SQLException{
		PreparedStatement ps = connection.prepareStatement(query);
		return ps.executeUpdate();
	}
	/***to execute the retrieve query as select returning the data***/
	public ResultSet executeRetrieveQuery() throws SQLException {
		Statement stmt = connection.createStatement();
		return stmt.executeQuery(query);
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
}
