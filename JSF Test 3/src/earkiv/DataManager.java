package earkiv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataManager {

	public Connection getConnection(String dbtype) throws Exception {

		Connection connection=null;

		if(dbtype=="mssql"){
			System.out.println(">> mssql");
			//try {
			String dbUrl = "jdbc:sqlserver://SE07334\\SQLEXPRESS;databaseName=earkiv";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection (dbUrl, "sa", "Streamserve1");
			//}

			//catch(ClassNotFoundException e) {
			//	e.printStackTrace();
			//	System.out.println("No connection to database - 1");
			//}

			//catch(SQLException e) {
			//	e.printStackTrace();
			//	System.out.println("No connection to database - 2");
			//}
		}
		else if(dbtype=="mysql"){
			System.out.println(">> mysql");
			//try {
			String dbUrl = "jdbc:mysql://localhost:3306/earkiv";
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl,"root", "");
			//} catch (Exception e) {
			//	e.printStackTrace();
			//	System.out.println("No connection to database - 3");
			//}
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

	public ArrayList<Document> searchDocuments(DataManager dataManager, Search search) throws Exception {

		ArrayList<Document> documents = new ArrayList<Document>();
		Connection connection = null;

		try {
			connection = dataManager.getConnection("mssql");
		} catch (Exception e) {
			System.out.println("Databas kopplingen sket sig: " + e);
			//e.printStackTrace();
			throw new SQLException();
		}

		String sql = "SELECT * FROM documents";

		// Create sql string
		if(search.getBgcId() !="") {
			sql = "SELECT * FROM documents WHERE bgcId LIKE '" + search.getBgcId() + "%'";
		}

		if(search.getTrackerId() !="") {
			sql = sql + " AND trackerId = '" + search.getTrackerId() + "'";
		}

		if(search.getScanDate() !=null) {
			sql = sql + " AND scanDate = '" + convertUtilToSql(search.getScanDate()) + "'";
		}
		System.out.println("SQL: "+sql);

		if (connection != null) {

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				Document document = new Document();
				document.setBgcId(rs.getString("bgcId"));
				document.setTrackerId(rs.getString("trackerId"));
				document.setInvoiceNumber(rs.getString("invoiceNumber"));
				document.setOCR(rs.getString("OCR"));
				document.setTotalAmount(rs.getString("totalAmount"));
				document.setScanDate(rs.getDate("scanDate"));

				//System.out.println("document: " + document);
				documents.add(document);
			}

			rs.close();
			statement.close();
			dataManager.closeConnection(connection);
		}

		return documents;
	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;

	}

}
