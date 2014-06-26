package seklund;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(description = "Main start servlet", urlPatterns = { "/Servlet" })
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected String myParam = null;

	//private DataSource dataSource;
	private Connection connection;
	//private Statement statement;
	String url = "jdbc:mysql://localhost:3306/strstest";		

	public Servlet() {
		super();
	}


	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilated");

		this.myParam = config.getInitParameter("myParam");



		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url,"root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doGet");

		ResultSet rs;



		try {
			System.out.println("URL: " + url);
			System.out.println("Connection: " + connection);

			Statement statement = connection.createStatement();
			//statement.executeUpdate("CREATE TABLE myTable(test_id int," +
			//		"test_val char(15) not null)");

			rs = statement.executeQuery("SELECT * " +
					"from cows ORDER BY age");

			System.out.println("Display all results:");

			while(rs.next()){
				String cowName= rs.getString("name");
				int cowAge = rs.getInt("age");
				System.out.println("\tCow= " + cowName
						+ "\tage = " + cowAge);
			}//end while loop

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*response.getWriter().write("<html><body>myParam = " +
				this.myParam + "</body></html>");
*/
		
		RequestDispatcher view = request.getRequestDispatcher("/jsp/index.jsp");
	    view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

}
