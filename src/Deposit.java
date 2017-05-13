

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class Deposit
 */
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
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
		System.out.println(" Deposit Section");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String s=request.getParameter("acc.no.");
		
		String q=request.getParameter("amt.");
		//out.print("<b>Welcome:<br>Acc.No."+s+"<br>Depsited Amount:"+q+"<br>");
		
try{
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			java.sql.PreparedStatement ps = con.prepareStatement("select * from cust where accno=?");
			
			ps.setString(1,s);
			java.sql.ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				if((rs.getString("acstatus")).equals("N"))
				{
					out.println("NEW ACCOUNT!!");
					ps=con.prepareStatement("update cust set acbal=?,acstatus=? where accno=?");
					ps.setString(1,q);
					ps.setString(2,"O");
					ps.setString(3,s);
					ps.executeUpdate();
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
					out.print("<br>AMOUNT DEPOSITED SUCCESSFULLY!!");
					rd.include(request,response);
				}
				
				else if((rs.getString("acstatus")).equals("O"))
				{
					 
					ps=con.prepareStatement("update cust set acbal=?,acstatus=? where accno=?");
					ps.setString(1,(Integer.parseInt(rs.getString("acbal"))+Integer.parseInt(q))+"");
					ps.setString(2,"O");
					ps.setString(3,s);
					ps.executeUpdate();
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
					out.print("<br>AMOUNT DEPOSITED SUCCESSFULLY!!!");
					rd.include(request,response);
				}
				
				else
				{
					out.println("<br>Sorry!! Account Is Closed!!");
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
					rd.include(request,response);
				}
				
			}
			
			else
			{
				out.print("<br>ACCOUNT DOES NOT EXIST!! ");
				RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
				rd.include(request,response);
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
