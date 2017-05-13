

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

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
		System.out.println(" Transfer Section");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String s=request.getParameter("sacc.no.");
		String t=request.getParameter("dacc.no.");
		String u=request.getParameter("amt.");
		
		
		
try{
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			java.sql.PreparedStatement ps = con.prepareStatement("select * from cust where accno=?");
			ps.setString(1,s);
			java.sql.ResultSet rs=ps.executeQuery();
			if(rs.next())
			{	
				int i=Integer.parseInt(rs.getString("acbal"));
				int j=Integer.parseInt(u);
				if((rs.getString("acstatus")).equals("N"))
				{
					out.println("ACCOUNT STATUS IS NEW !! WITHDRAWL NOT POSSIBLE DUE TO INSUFFICIENT BALANCE !!");
					 
				}
				
				else if((rs.getString("acstatus")).equals("O"))
				{
						if(i>=j){
							ps=con.prepareStatement("update cust set acbal=?,acstatus=? where accno=?");
							ps.setString(1,(Integer.parseInt(rs.getString("acbal"))-Integer.parseInt(u))+"");
							ps.setString(2,"O");
							ps.setString(3,s);
							PreparedStatement ps1=(PreparedStatement) con.prepareStatement("select * from cust where accno=?");
							ps1.setString(1,t);
							ResultSet rs1=(ResultSet) ps1.executeQuery();
							if(rs1.next())
							{
								if((rs1.getString("acstatus")).equals("N"))
								{
									out.println("DESTINATION ACCOUNT STATUS IS NEW!!");
									ps1=(PreparedStatement) con.prepareStatement("update cust set acbal=?,acstatus=? where accno=?");
									ps1.setString(1,u);
									ps1.setString(2,"O");
									ps1.setString(3,t);
									ps1.executeUpdate();
									
									ps.executeUpdate();
									RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
									out.print("AMOUNT TRANSFERED SUCCESSFULLY!!");
									rd.include(request,response);
								}
								
								else if((rs1.getString("acstatus")).equals("O"))
								{
									 
									ps1=(PreparedStatement) con.prepareStatement("update cust set acbal=?,acstatus=? where accno=?");
									ps1.setString(1,(Integer.parseInt(rs1.getString("acbal"))+Integer.parseInt(u))+"");
									ps1.setString(2,"O");
									ps1.setString(3,t);
									ps1.executeUpdate();
									 
									ps.executeUpdate();
									RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
									out.print("AMOUNT TRANSFERED SUCCESSFULLY!!");
									rd.include(request,response);
								}
								
								else
								{
									out.println("DESTINATION ACCOUNT IS CLOSED!!");
									RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
									rd.include(request,response);
								}
								
							}
							
							else
							{
								out.print("INVALID DESTINATION ACCOUNTNUMBER");
								RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
								rd.include(request,response);
							}
						}
							 
							 else
							 {
								 	out.println("INSUFFICIENT BALANCE!");
									RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
									rd.include(request,response);
							 }
								 
			  }
				
				else
				{
					out.println("SORRY IS CLOSED!!");
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
					rd.include(request,response);
				}
				
			}
			
			else
			{
				out.print("INVALID SOURCE ACCOUNTNUMBER");
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
