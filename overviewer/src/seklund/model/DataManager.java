package seklund.model;

import java.sql.*;
import java.util.ArrayList;




public class DataManager {

	private String dbType;
	private String dbURL;
	private String dbUser;
	private String dbUserPwd;

	public DataManager(String dbType, String dbURL, String dbUser, String dbUserPwd) {
		this.dbType = dbType;
		this.dbURL = dbURL;
		this.dbUser = dbUser;
		this.dbUserPwd = dbUserPwd;
	}

	public Connection getConnection() {

		Connection connection = null;
		System.out.println("getConnection-dbType: " + dbType);

		if(dbType.equals("mssql")) {
			
			System.out.println(">> mssql");
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection (dbURL, "sa", "Streamserve1");
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
		else if(dbType.equals("mysql")){
			System.out.println(">> mysql");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dbURL,"root", "");
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

	public ArrayList<Tenant> getTenants(Connection connection) {

		System.out.println("Datamanager - getTenants");

		ArrayList<Tenant> tenants = new ArrayList<Tenant>();

		if(connection != null) {
			try {
				Statement statement = connection.createStatement();
				String sql = "SELECT * from tenant";

				try {

					ResultSet rs = statement.executeQuery(sql);
					//System.out.println("resultset: " + rs);
					
					try {
						while (rs.next()) {
							//System.out.println("rs: "+ rs.getString(1));
							//System.out.println("rs: "+ rs.getString(2));
							//System.out.println("rs: "+ rs.getString(3));
							
							Tenant tenant = new Tenant();
							tenant.setTenantId(rs.getString(1));
							tenant.setName(rs.getString(2));
							tenant.setDescription(rs.getString(3));
							tenants.add(tenant);
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
				System.out.println("Could not get any tenants: " + e.getMessage());
			}
			finally {
				
			}
		}
		else {
			System.out.println("No connection to database, need to re-connect");
		}

		return tenants;
	}
	
	public ArrayList<String> getTenantResources(Connection connection, String tenantId) {
		
		ArrayList something = new ArrayList();
		something.add("jolo");
		
		
		
		return something;
	}


}
