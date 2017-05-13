

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String s=request.getParameter("name");
		String q=request.getParameter("address");
		String w=request.getParameter("pwd");
		String e=request.getParameter("age");
		String r=request.getParameter("aadhar");
		out.print(s+" You are successfully Registered");
		
		
try{
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			java.sql.PreparedStatement ps = con.prepareStatement("insert into emp(name,address,pwd,age,adhaar)values(?,?,?,?,?)");
			ps.setString(1,s);
			ps.setString(2,q);
			ps.setString(3,w);
			ps.setString(4,e);
			ps.setString(5,r);
			
			
			int i = ps.executeUpdate();
			if(i>0){
				RequestDispatcher  rd=request.getRequestDispatcher("login.jsp"); 
				rd.include(request, response);
			}	
			
			else 
			{
				RequestDispatcher  rd=request.getRequestDispatcher("register.jsp"); 
				rd.include(request, response);
				
			}
					
		}catch(Exception e1){
			e1.printStackTrace();
			
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

}
