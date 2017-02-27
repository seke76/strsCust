package seklund.model;

import java.sql.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;




public class DataManager {
	
	final static Logger logger = Logger.getLogger(seklund.servlet.class);
	
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
		logger.info("getConnection-dbType: " + dbType);
		
		if(dbType.equals("mssql")) {
			
			logger.info(">> mssql");
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection (dbURL, "sa", "Streamserve1");
			}

			catch(ClassNotFoundException e) {
				e.printStackTrace();
				logger.info("No connection to database - 1: " + e);
				logger.info(e.getMessage());
			}

			catch(SQLException e) {
				e.printStackTrace();
				logger.info("No connection to database - 2: " + e);
				logger.info(e.getMessage());
			}
		}
		else if(dbType.equals("mysql")){

			logger.info(">> mysql");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dbURL,"root", "");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("No connection to database - 3: " + e);
				logger.info(e.getMessage());
			}
		}

		logger.info("connection: " + connection);
		return connection;
	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				logger.info("DataManager, close connection error: " + e);
			}

			logger.info("Database closed");
		}
	}

	public ArrayList<Tenant> getTenants(Connection connection) {

		logger.info("Datamanager - getTenants");

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
				logger.info("Could not get any tenants: " + e.getMessage());
			}
			finally {
				
			}
		}
		else {
			logger.info("No connection to database, need to re-connect");
		}

		return tenants;
	}
	
	public ArrayList<String> getTenantResources(Connection connection, String tenantId) {
		
		logger.info("Datamanager - getTenantResources");
		ArrayList<String> tenantresources = new ArrayList<String>();

		
		
		if(connection != null) {
			try {
				Statement statement = connection.createStatement();
				String sql = "SELECT resources.ResourceID, resources.DomainID, resources.NodeID, resources.Name, resources.Description, resources.ContentType, resources.StrsType, resources.ContentEncoding, resources.Data, resources.Version, resources.Revision, resources.MD5, resources.CreationDateTime, resources.LastUpdateTime, resources.LogicalID, resources.SoftwareVersion, resources.TenantID FROM resources LEFT JOIN relresourcestenant ON resources.ResourceID=relresourcestenant.ResourceID WHERE relresourcestenant.TenantID='1BDAE524-BB85-3E4A-97F1-87FECB72C8BF'";

				try {

					ResultSet rs = statement.executeQuery(sql);


					try {
						while (rs.next()) {
							System.out.println("rs: "+ rs.getString(1));
							System.out.println("rs: "+ rs.getString(2));
							System.out.println("rs: "+ rs.getString(3));
						/*
							TenantResource tenantresource = new TenantResource();
							tenant.setTenantId(rs.getString(1));
							tenant.setName(rs.getString(2));
							tenant.setDescription(rs.getString(3));
							tenants.add(tenant);
						*/
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
				logger.info("Could not get any tenant resources: " + e.getMessage());
			}
			finally {
				
			}
		}
		else {
			logger.info("No connection to database, need to re-connect");
		}
		
		return tenantresources;
	}


}
