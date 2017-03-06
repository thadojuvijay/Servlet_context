package info.servlet_servletcontextdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_ContextDemo
 */
@WebServlet("/Servlet_ContextDemo")
public class Servlet_ContextDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_ContextDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext servletContext = getServletContext();
		String dname = servletContext.getInitParameter("driver");
String url = servletContext.getInitParameter("durl");
String username = servletContext.getInitParameter("username");
String pass = servletContext.getInitParameter("password");

		String no = request.getParameter("eno");
		String name = request.getParameter("ename");
          String sal = request.getParameter("esalary");
		try {
	Class.forName(dname);
	
	Connection connection = DriverManager.getConnection(url,username,pass);
	Statement statement = connection.createStatement();
	
	statement.executeUpdate("insert into employee values('"+no+"','"+name+"','"+sal+"')");
	connection.close();
	PrintWriter out = response.getWriter();
	out.println("record inserted");
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

}
