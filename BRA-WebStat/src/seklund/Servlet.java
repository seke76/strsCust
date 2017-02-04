package seklund;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(description = "Main start servlet", urlPatterns = { "/Servlet" })
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected String myParam = null;

	private Connection connection;
	
	public Servlet() {
		super();
	}


	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("Servlet initilated");

		this.myParam = config.getInitParameter("myParam");

		DataManager datamanager = new DataManager();
		
		ServletContext context = config.getServletContext();
		context.setAttribute("base", config.getInitParameter("base"));
		context.setAttribute("imageURL", config.getInitParameter("imageURL"));
		context.setAttribute("dataManager", datamanager);
		
/*		try {
			connection = datamanager.getConnection("mssql");
			//datamanager.closeConnection(connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} */
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doGet");

		ResultSet rs=null;
		String con="Not sure";


		/*response.getWriter().write("<html><body>myParam = " +
				this.myParam + "</body></html>");
		 */
		
		
		request.setAttribute("connection", con);
		request.setAttribute("resultset", rs);
		RequestDispatcher view = request.getRequestDispatcher("/jsp/index.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

}
