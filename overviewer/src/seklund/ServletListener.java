package seklund;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import seklund.model.DataManager;


@WebListener
public class ServletListener implements ServletContextListener {

    public ServletListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextInitialized(ServletContextEvent event)  {
    	
    	System.out.println("Context Initiated in overviewer listener");
    	
		ServletContext servletContext = event.getServletContext();
		
		String dbType = servletContext.getInitParameter("dbType");
		String dbURL = servletContext.getInitParameter("dbURL");
		String dbUser = servletContext.getInitParameter("dbUser");
		String dbUserPwd = servletContext.getInitParameter("dbUserPwd");
		
		System.out.println("dbType: " + dbType);
		
		// Create a dbconnection object
		DataManager datamanager = new DataManager(dbType, dbURL, dbUser, dbUserPwd);
		Connection connection = datamanager.getConnection();
		
		//Add object to servletContext as a attribute
		servletContext.setAttribute("datamanager", datamanager);
		servletContext.setAttribute("connection", connection);
   }

    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("DESTROY THE WORLD!!!!");
    }

	
}
