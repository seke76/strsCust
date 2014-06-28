package seklund;

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
			}

			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else if(dbtype=="mysql"){
			System.out.println(">> mysql");
			try {
				String dbUrl = "jdbc:mysql://localhost:3306/strstest";
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dbUrl,"root", "");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		//System.out.println(">> connection: " + connection);
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

	public ArrayList<Cow> getCows(DataManager dataManager) {

		ArrayList<Cow> cows = new ArrayList<Cow>();
		Connection connection = dataManager.getConnection("mssql");
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String sql = "SELECT * from cows ORDER BY age";
				try {
					ResultSet rs = statement.executeQuery(sql);
					try {
						while (rs.next()) {
							Cow cow = new Cow();
							cow.setName(rs.getString(1));
							cow.setAge(rs.getInt(2));
							cows.add(cow);
						}
					}
					finally { rs.close(); }
				}
				finally { statement.close(); }
			}
			catch (SQLException e) {
				System.out.println("Could not get any cows: " + e.getMessage());
			}
			finally {
				dataManager.closeConnection(connection);
			}
		}

		return cows;
	}

}
