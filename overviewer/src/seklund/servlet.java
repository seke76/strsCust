package seklund;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import seklund.model.DataManager;
import seklund.model.Tenant;

@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(seklund.servlet.class);
       
    public servlet() {
        super();
    }

   
	public void init() throws ServletException {
		
		System.out.println("Init");
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("DoGet");
		
		ServletContext context = getServletContext();
		DataManager datamanager = (DataManager) context.getAttribute("datamanager");
		Connection connection = (Connection) context.getAttribute("connection");
		
		String forward = "jsp/index.jsp";
		String action = request.getParameter("action");

		System.out.println("Get action: " + action);
		logger.info("Get action : " + action);
		
        if (action.equalsIgnoreCase("tenant")){
        	String tenantId = request.getParameter("tenantId");
            System.out.println("tenantId: " + tenantId);
        	
            datamanager.getTenantResources(connection, tenantId);
            
            forward = "jsp/tenantresources.jsp";
            //dao.deleteUser(userId);
            //request.setAttribute("users", dao.getAllUsers());
        } else if (action.equalsIgnoreCase("edit")){
            forward = "INSERT_OR_EDIT";
            int userId = Integer.parseInt(request.getParameter("userId"));
            //User user = dao.getUserById(userId);
            //request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = "LIST_USER";
            //request.setAttribute("users", dao.getAllUsers());
        } else {
            forward = "INSERT_OR_EDIT";
        }
        
		//  Forwards the request to the jsp
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("DoPost");
		String forward = "jsp/index.jsp";
		
		ServletContext context = getServletContext();
		DataManager datamanager = (DataManager) context.getAttribute("datamanager");
		Connection connection = (Connection) context.getAttribute("connection");
		
		
		//get request parameters for userID and password
		String userSelect = request.getParameter("userSelect");
		System.out.println("Selection: " + userSelect);
		logger.info("Posting, userSelection: " + userSelect);
		
		String password = context.getInitParameter("dbUserPwd");  
		
		System.out.println("Init attr: " + getServletContext().getAttribute("dbURL") );
		System.out.println("Init param: " + getServletContext().getInitParameter("dbURL") );

		
		if(userSelect.equals("config") ){
			forward = "jsp/config.jsp";
		}
		else if(userSelect.equals("MT") ){
			
		}
		else if(userSelect.equals("tenants")){
			
			forward = "jsp/tenants.jsp";
			ArrayList<Tenant> tenants = datamanager.getTenants(connection);
			//context.setAttribute("tenants", tenants);
			request.setAttribute("tenants", tenants);
		}
		else if(userSelect.equals("nodes") ){
			forward = "jsp/nodes.jsp";
		}

		
		
		//response.sendRedirect("./jsp/hello.jsp");
	
		//  Forwards the request to the jsp
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

}
