package com.lautaroOlmedo.webProducts;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Servlet implementation class ServletPruebas
 */
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ESTABLECER EL DATASOURCE
	@Resource(name = "jdbc/WebProducts")
	private DataSource myPool;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter salida = response.getWriter();
		response.setContentType("text/plain");
		
		// CONEXIÓN CON DB
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet rs = null;
		
		try {
			myConnection = myPool.getConnection();
			
			String SqlStaement = "SELECT * FROM PRODUCTOS";
			
			myStatement = myConnection.createStatement();
			
			rs = myStatement.executeQuery(SqlStaement);
			
			while(rs.next()) {
				String productName = rs.getString(3);
				salida.println(productName);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
