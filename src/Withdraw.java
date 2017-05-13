

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
 * Servlet implementation class Withdraw
 */
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
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
		System.out.println(" Withdraw Section");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String s=request.getParameter("acc.no.");
		String q=request.getParameter("amt.");
		
		
		
		
try{
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			java.sql.PreparedStatement ps = con.prepareStatement("select*from cust where accno=?");
			ps.setString(1,s);
			
			java.sql.ResultSet rs=ps.executeQuery();
			if(rs.next())
			{	
				int i=Integer.parseInt(rs.getString("acbal"));
				int j=Integer.parseInt(q);
				if((rs.getString("acstatus")).equals("N"))
				{
					out.println("ACCOUNT STATUS IS NEW !! WITHDRAWL NOT POSSIBLE  !!");
					RequestDispatcher rd=request.getRequestDispatcher("Withdraw.jsp");
					rd.include(request,response);
				}
				
				else if((rs.getString("acstatus")).equals("O"))
				{
					 if(i>=j){
					ps=con.prepareStatement("update cust set acbal=?,acstatus=? where accno=?");
					ps.setString(1,(Integer.parseInt(rs.getString("acbal"))-Integer.parseInt(q))+"");
					ps.setString(2,"O");
					ps.setString(3,s);
					ps.executeUpdate();
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
					out.print("AMOUNT WITHDRAW SUCCESSFULLY!!");
					rd.include(request,response);}
					 else
					 {
						 	out.println("SORRY!!LOW BALANCE!!!");
							RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
							rd.include(request,response);
					 }
						 
				}
				
				else
				{
					out.println("ACCOUNT STATUS IS CLOSE!");
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
					rd.include(request,response);
				}
				
			}
			
			else
			{
				out.print("ACCOUNT DOES NOT EXIST");
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
