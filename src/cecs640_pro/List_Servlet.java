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
 * Servlet implementation class List_Servlet
 */
@WebServlet("/List_Servlet")
public class List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ResultSet          resultSet        = null;
    public Statement          statement        = null;
    public String             sqlResults        = "";
    Connection connection=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Servlet() {
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
		String table=request.getParameter("table");
		String sqlStatement="";
		if(table.equalsIgnoreCase("Users_List"))
		{
			sqlStatement="SELECT * FROM L0LV0002.USERS_LIST";
		}
		else if(table.equalsIgnoreCase("Favoriate_Book"))
			sqlStatement="SELECT * FROM L0LV0002.FAVORIATE_BOOK";
		else
			sqlStatement="SELECT * FROM L0LV0002.BOOK_INFO";
		ConnectionPool pool = ConnectionPool.getInstance("jdbc/L0LV0002");
        connection = pool.getConnection();
        parseAndExecute(sqlStatement, connection);
        pool.freeConnection(connection);
        HttpSession session = request.getSession();
        session.setAttribute("sqlResults", sqlResults);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/List.jsp");
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
            	try
            	{
            		resultSet=statement.executeQuery(sqlStatement);
            		sqlResults=SQLUtil.getHtmlTable(resultSet);
            		resultSet.close();
            	}
            	catch(SQLException e)
            	{
            		System.out.println("Cannot execute query");
                    e.printStackTrace();
            	}
            }
		}
		catch(SQLException e)
		{
			System.out.println("Could not create statment: " + e.getMessage());
		}
	}

}
