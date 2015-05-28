http://docs.oracle.com/javaee/6/tutorial/doc/gjaam.html
http://www.vogella.com/tutorials/JavaServerFaces/article.html
http://www.mkyong.com/tutorials/jsf-2-0-tutorials/

JavaServer Faces technology provides an easy and user-friendly process for creating web applications.
Developing a simple JavaServer Faces application typically requires the following tasks:

- Developing managed beans
- Creating web pages using component tags
- Mapping the javax.faces.webapp.FacesServlet instance

A managed bean is a lightweight container-managed object.
Components in a page are associated with managed beans that provide application logic.

The web page connects to the managed bean through the Expression Language (EL) value expression #{hello.world},
which retrieves the value of the world property from the managed bean Hello.
Note the use of hello to reference the managed bean Hello.
If no name is specified in the @ManagedBean annotation,
the managed bean is always accessed with the first letter of the class name in lowercase. 

Knark2