package seklund;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import seklund.model.DataManager;


@WebListener
public class ServletListener implements ServletContextListener {
	final static Logger logger = Logger.getLogger(seklund.ServletListener.class);

    public ServletListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextInitialized(ServletContextEvent event)  {
    	
    	System.out.println("Context Initiated in overviewer listener");
    	logger.info("Listener is starting, context initialazed");
    	
		ServletContext servletContext = event.getServletContext();
		
		String dbType = servletContext.getInitParameter("dbType");
		String dbURL = servletContext.getInitParameter("dbURL");
		String dbUser = servletContext.getInitParameter("dbUser");
		String dbUserPwd = servletContext.getInitParameter("dbUserPwd");
		
		System.out.println("dbType: " + dbType);
		
		// Create a dbconnection object
		DataManager datamanager = new DataManager(dbType, dbURL, dbUser, dbUserPwd);
		Connection connection = datamanager.getConnection();
		
		logger.info("Connection is created: " + connection);
		
		//Add object to servletContext as a attribute
		servletContext.setAttribute("datamanager", datamanager);
		servletContext.setAttribute("connection", connection);
   }

    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("DESTROY THE WORLD!!!!");
    	logger.info("Container destroys the servlet");
    }

	
}
