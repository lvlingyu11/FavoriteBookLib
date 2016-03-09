package cecs640_pro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cecs640_pro.ConnectionPool;

/**
 * Servlet implementation class Update_Servlet
 */
@WebServlet("/Update_Servlet")
public class Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ResultSet          resultSet        = null;
    public Statement          statement        = null;
    public String             sqlResult        = "";
    Connection connection=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_Servlet() {
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
		String sqlStatement = request.getParameter("sqlStatement");
        ConnectionPool pool = ConnectionPool.getInstance("jdbc/L0LV0002");
        connection = pool.getConnection();
        parseAndExecute(sqlStatement, connection);
        pool.freeConnection(connection);
        HttpSession session = request.getSession();
        session.setAttribute("sqlResult", sqlResult);
        session.setAttribute("sqlStatement", sqlStatement);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Update.jsp");
        dispatcher.forward(request, response);
	}
	
	 private void parseAndExecute(String sqlStatement, Connection connection)
	 {
		 try
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
	                        sqlResult = SQLUtil.getHtmlTable(resultSet);
	                        resultSet.close();
	                    }
	                    catch (SQLException e)
	                    {
	                        System.out.println("Cannot execute query");
	                        e.printStackTrace();
	                    }
	                }
	                else
	                {
	                    try
	                    {
	                        int i = -1;
	                        i = statement.executeUpdate(sqlStatement);
	                        if (i == 0)
	                        {
	                            sqlResult = "The statement executed successfully.";
	                        }
	                        else
	                        {
	                            sqlResult = "The statement executed successfully.<br>" + i + " row(s) affected.";
	                        } // end if
	                    }
	                    catch (SQLException e)
	                    {
	                        System.out.println("Cannot execute query");
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	        catch (SQLException e)
	        {
	            System.out.println("Could not create statment: " + e.getMessage());
	        }
	    }

}
