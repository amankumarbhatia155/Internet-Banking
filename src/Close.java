

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
 * Servlet implementation class Close
 */
public class Close extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Close() {
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String s=request.getParameter("acc.no.");
		
		
		
try{
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			java.sql.PreparedStatement ps = con.prepareStatement("select * from cust where accno=?");
			
			ps.setString(1,s);
			
			java.sql.ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				ps=con.prepareStatement("update cust set acstatus=? where accno=?");
				ps.setString(1,"C");
				ps.setString(2,s);
				 ps.executeUpdate();
				 out.print("Account No."+s+"is Closed!!");
				 RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
				 rd.include(request,response);
			}
			else
			
			{
				out.print("Account Does not Exist!!");
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
