

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("hello in Service");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String a=request.getParameter("usrname");
		String b=request.getParameter("pwd");
		
		if(a.equals("")|| b.equals(""))
		{
			out.print("Enter Username and password");
			RequestDispatcher  rd=request.getRequestDispatcher("login.jsp"); 
			rd.include(request, response);
			
		}
	
		
		else {	
	
try{
	ServletContext sc=getServletContext();
	String driver =sc.getInitParameter("driver");
	
			Class.forName(driver);
			java.sql.Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			java.sql.PreparedStatement ps = con.prepareStatement("select * from emp where name=? and pwd=?");
			ps.setString(1,a);
			ps.setString(2,b);
			
			
			
			
			java.sql.ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher  rd=request.getRequestDispatcher("home.jsp"); 
				rd.forward(request, response);
			}
			else {
				out.print("No User Available");
				RequestDispatcher  rd=request.getRequestDispatcher("login.jsp"); 
				rd.include(request, response);
			}
			

		
	}
catch(Exception e)
{
	e.printStackTrace();
}
		}
	}



	 
	
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
