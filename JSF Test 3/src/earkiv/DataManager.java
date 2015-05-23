package earkiv;

import hello.Todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataManager {

	public Connection getConnection(String dbtype) {

		Connection connection=null;

		if(dbtype=="mssql"){
			System.out.println(">> mssql");
			try {
				String dbUrl = "jdbc:sqlserver://localhost;databaseName=strstest";
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection (dbUrl, "sa", "Streamserve1");
			}

			catch(ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("No connection to database - 1");
			}

			catch(SQLException e) {
				e.printStackTrace();
				System.out.println("No connection to database - 2");
			}
		}
		else if(dbtype=="mysql"){
			System.out.println(">> mysql");
			try {
				String dbUrl = "jdbc:mysql://localhost:3306/earkiv";
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dbUrl,"root", "");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("No connection to database - 3");
			}
		}

		System.out.println(">> connection: " + connection);
		return connection;
	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				System.out.println("DataManager, close connection error");
			}
			System.out.println("Database closed");
		}
	}
	
	public ArrayList<Document> searchDocuments(DataManager dataManager, Search search) {
		
		ArrayList<Document> documents = new ArrayList<Document>();
		Connection connection = dataManager.getConnection("mysql");
		
		String sql = "SELECT * FROM document";
		
		// Create sql string
		if(search.getBgcId() !="") {
			sql = "SELECT * FROM document WHERE bgcId LIKE '" + search.getBgcId() + "%'";
		}
		
		if(search.getTrackerId() !="") {
			sql = "SELECT * FROM document WHERE trackerId = '" + search.getTrackerId() + "'";
		}
		
		if (connection != null) {

			try {
				Statement statement = connection.createStatement();
				
				try {

					ResultSet rs = statement.executeQuery(sql);
					try {
						while (rs.next()) {
							Document document = new Document();
							document.setBgcId(rs.getString("bgcId"));
							document.setTrackerId(rs.getString("trackerId"));
							//System.out.println("todo: " + todo);
							documents.add(document);
						}
					}
					finally {
						rs.close();
					}
				}
				finally {
					statement.close();
				}
			}
			catch (SQLException e) {
				System.out.println("Could not get any cows: " + e.getMessage());
			}
			finally {
				dataManager.closeConnection(connection);
			}
		}
		
		return documents;
	}
}
