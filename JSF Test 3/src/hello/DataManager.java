package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
				String dbUrl = "jdbc:mysql://localhost:3306/test";
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

	public ArrayList<Todo> getTodos(DataManager dataManager) {

		System.out.println("get todos methos");

		ArrayList<Todo> todos = new ArrayList<Todo>();
		Connection connection = dataManager.getConnection("mysql");

		if (connection != null) {

			try {
				Statement statement = connection.createStatement();
				String sql = "SELECT * from todo";

				try {

					ResultSet rs = statement.executeQuery(sql);
					//System.out.println("resultset: " + rs);
					try {
						while (rs.next()) {
							Todo todo = new Todo(rs.getString(2), rs.getString(3), rs.getInt(4));
							todo.setId(rs.getInt("id"));
							//System.out.println("todo: " + todo);
							todos.add(todo);
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

		return todos;
	}

	public void saveTodo(DataManager dataManager, Todo todo) {

		System.out.println("Save method");
		Connection connection = dataManager.getConnection("mysql");

		if (connection != null) {

			try {

				String insertTableSQL = "INSERT INTO todo"
						+ "(title, description, priority) VALUES"
						+ "(?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, todo.getTitle());
				preparedStatement.setString(2, todo.getDescription());
				preparedStatement.setInt(3, todo.getPriority());

				preparedStatement .executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	public void deleteTodo(DataManager dataManager, Todo todo) {

		System.out.println("Deleting todo");
		
		try{

			Connection connection = dataManager.getConnection("mysql");
			
			String sql = "DELETE FROM todo WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, todo.getId());
			preparedStatement .executeUpdate();
			preparedStatement.close();
			System.out.println("Lille todo is gone");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
