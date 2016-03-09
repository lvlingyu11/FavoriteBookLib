package cecs640_pro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cecs640_pro.ConnectionPool;
/**
 * Servlet implementation class Pro_Login_Servlet
 */
@WebServlet("/Pro_Login_Servlet")
public class Pro_Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ResultSet          resultSet        = null;
    public Statement          statement        = null;
    public String             sqlResult        = "";
    Connection connection=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pro_Login_Servlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String userID=request.getParameter("User_ID");
		String password=request.getParameter("Password");
		String sqlStatement="SELECT USER_PW FROM L0LV0002.USERS_LIST WHERE USER_ID=\'"+userID+"\'AND USER_PW=\'"+password+"\'";
		ConnectionPool pool = ConnectionPool.getInstance("jdbc/L0LV0002");
        connection = pool.getConnection();
		String url="/Menu.jsp";
		if (userID.length()==0 || password.length()==0)
		{
			url="/Pro_Login.jsp";
			pool.freeConnection(connection);
			request.setAttribute("error", "Both user ID and password can not be null!");
			ServletContext context = getServletContext();
    	    RequestDispatcher dispatcher = context.getRequestDispatcher(url);
    	    dispatcher.forward(request, response);
		}
		else if(userID.length()>20 || password.length()>15)
		{
			url="/Pro_Login.jsp";
			pool.freeConnection(connection);
			request.setAttribute("error", "Please make sure that the length of user ID is not larger than 20 and password is not larger than 15!");
			ServletContext context = getServletContext();
    	    RequestDispatcher dispatcher = context.getRequestDispatcher(url);
    	    dispatcher.forward(request, response);
		}
		else
		{
			try {
				boolean flag=parseAndExecute(sqlStatement, connection);
				pool.freeConnection(connection);
				if(flag == true)
				{
					ServletContext context = getServletContext();
				    RequestDispatcher dispatcher = context.getRequestDispatcher(url);
	    	        dispatcher.forward(request, response);
				}
				else
				{
					url="/Pro_Login.jsp";
					request.setAttribute("error", "Invalid User ID or Password");
					 ServletContext context = getServletContext();
		    	    RequestDispatcher dispatcher = context.getRequestDispatcher(url);
		    	    dispatcher.forward(request, response);
				}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	 private boolean parseAndExecute(String sqlStatement, Connection connection) throws SQLException
	    {
	            statement = connection.createStatement();
	            sqlStatement = sqlStatement.trim();
	            if (sqlStatement.length() >= 6)
	            {
	                String sqlType = sqlStatement.substring(0, 6);
	                if (sqlType.equalsIgnoreCase("select"))
	                {
	                    try
	                    {
	                        resultSet = statement.executeQuery(sqlStatement);
	                        //sqlResult = SQLUtil.getHtmlTable(resultSet);
	                        if(resultSet.next())
	                        	return true;
	                        else 
	                        	return false;
	                    }
	                    catch (SQLException e)
	                    {
	                        System.out.println("Cannot execute query");
	                        e.printStackTrace();
	                        return false;
	                    }
	                }
	                else 
	                	return false;
	            }
	            else
	            	return false;
				
	    }

}
