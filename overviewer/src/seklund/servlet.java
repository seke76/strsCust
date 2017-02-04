package seklund;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servlet() {
        super();
    }


    
	public void init() throws ServletException {
		
		//we can create DB connection resource here and set it to Servlet context
		if(getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysql_db") &&
				getServletContext().getInitParameter("dbUser").equals("mysql_user") &&
				getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd"))
		getServletContext().setAttribute("DB_Success", "True");
		else throw new ServletException("DB Connection error");
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("DoGet");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("DoPost");
		
		//get request parameters for userID and password
		
		String userSelect= request.getParameter("userSelect");
		System.out.println("Selection: " + userSelect);
		
		//get servlet config init params
		String userID = getServletConfig().getInitParameter("dbUser");
		//String password = getServletContext("dbUserPwd");
		//creating ServletContext object  
		ServletContext context=getServletContext();
		String password=context.getInitParameter("dbUserPwd");  
		
		//logging example
		System.out.println("User="+userID+"::password="+password);
		
		if(userSelect=="config"){
			
		}
		else if(userSelect=="MT"){
			
		}
		else if(userSelect=="T"){
			
		}
		else if(userSelect=="app"){
			
		}

		
		response.sendRedirect("./jsp/hello.jsp");
	
//		doGet(request, response);

	}

}
