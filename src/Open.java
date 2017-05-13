

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
 * Servlet implementation class Open
 */
public class Open extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Open() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//		
//	}

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
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String s=request.getParameter("name");
		String q=request.getParameter("address");
		String r=request.getParameter("age");
		String t=request.getParameter("contact");
		String u=request.getParameter("sex");
		String v=request.getParameter("adhaar");
		
		
		
//		if(s.equals("") || q.equals("") || r.equals("") || t.equals("") || u.equals("") || v.equals("")){
//			out.print("All Fields are Mandatory. Please complete all");
//			RequestDispatcher  rd=request.getRequestDispatcher("open.jsp"); 
//			rd.include(request, response);
//		}
//		
//		else{
//		
		
try{
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			java.sql.PreparedStatement ps = con.prepareStatement("insert into cust(name,age,address,contact,adhaarno,gender,acbal,acstatus)values(?,?,?,?,?,?,?,?)");
			ps.setString(1,s);
			ps.setString(2,r);
			ps.setString(3,q);
			ps.setString(4,t);
			ps.setString(5,v);
			ps.setString(6,u);
			ps.setString(7,"0");
			ps.setString(8,"N");
			
			
			
			
			int i = ps.executeUpdate();
			if(i>0){
				out.print("<br>Account is successfully created ");
				RequestDispatcher  rd=request.getRequestDispatcher("home.jsp"); 
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
