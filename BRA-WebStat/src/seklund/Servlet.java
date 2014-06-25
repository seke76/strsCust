package seklund;

import java.io.IOException;
import java.sql.*;

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

	public Servlet() {
		super();
	}


	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilated");

		this.myParam = config.getInitParameter("myParam");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doGet");

	
			
	
		String url =
				"jdbc:mysql://localhost:3306/strstest";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url,"strs", "streamserve");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Statement statement = connection.createStatement();
			//statement.executeUpdate("CREATE TABLE myTable(test_id int," +
				//	"test_val char(15) not null)");
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}



		response.getWriter().write("<html><body>myParam = " +
				this.myParam + "</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

}
