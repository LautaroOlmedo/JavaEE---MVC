package com.lautaroOlmedo.webProducts;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

public class ProductsModel {
	
	// ---------- ---------- ---------- CONSTRUCTOR ---------- ---------- ----------
	
	public ProductsModel(DataSource origenData) {
		this.origenData = origenData;
	}
	
	public List<Products> getProducts() throws Exception{
		
		List<Products> productsList = new ArrayList<>();
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet rs = null;
		
		try {
			// ---------- CREAR CONEXIÓN ---------- 
			myConnection = this.origenData.getConnection();
			
			// ---------- CREAR SENTENCIA SQL Y STATEMENT ---------- 
			String sqlStatement = "SELECT * FROM PRODUCTOS";
			myStatement = myConnection.createStatement();
			
			// ---------- EJECUTAR SENTENCIA SQL ---------- 
			rs = myStatement.executeQuery(sqlStatement);
			
			// ---------- LEER Y RECORRES RESULTSET ---------- 
			while(rs.next()) {
				String cArt = rs.getString("CÓDIGOARTÍCULO");
				String section = rs.getString("SECCIÓN");
				String nArt = rs.getString("NOMBREARTÍCULO");
				double price = rs.getDouble("PRECIO");
				String oCountry = rs.getString("PAÍSDEORIGEN");
				Date date = rs.getDate("FECHA");
				boolean imported = rs.getBoolean("IMPORTADO");
				
				Products tempProd = new Products(cArt, section, nArt, price, imported, oCountry, date);
				
				productsList.add(tempProd);
			}
		}catch(Exception e) {
			
		}finally {
			myConnection.close();
			rs.close();
		}
		return productsList;
	}
	
	public void addProductModel(Products myProduct) {
		// TODO Auto-generated method stub
		
		// OBETENER CONEXIÓN CON DB
		Connection myConnection;
		java.sql.PreparedStatement myStatement;
		
		try {
			myConnection = origenData.getConnection();
			
			// CREAR INSTRUCCIÓN SQL PARA INSERTAR EL PRODUCT
			
			String sql = "INSERT INTO PRODUCTOS (CÓDIGOARTÍCULO, SECCIÓN, NOMBREARTÍCULO, PRECIO, FECHA, IMPORTADO, PAÍSDEORIGEN)" +
			"VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			myStatement = myConnection.prepareStatement(sql);
			
			// ESTABLECER PARAMETRO PARA EL PRODUCT
			myStatement.setString(1, myProduct.getcArt());
			myStatement.setString(2, myProduct.getSection());
			myStatement.setString(3, myProduct.getnArt());
			myStatement.setDouble(4, myProduct.getPrice());
			
			java.util.Date utilDate = myProduct.getDate();
			java.sql.Date convertDate = new java.sql.Date(utilDate.getTime());
			
			myStatement.setDate(5, convertDate);
			myStatement.setBoolean(6, false);
			myStatement.setString(7, myProduct.getoCountry());
			
		

			// EJECUTAR LA INSTRUCCIÓN SQL 
			myStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Products getProduct(String cArt) {
		// TODO Auto-generated method stub
		Products myProduct = null;
		Connection myConnection = null;
		ResultSet rs = null;
		java.sql.PreparedStatement myStatement = null;
		String codigoArt = cArt;
		
		
		try {
		// ESTABLECER CONEXION 
			myConnection = origenData.getConnection();
		
		// CREAR SQL QUE BUSQUE EL PRODUCT
			String sql = "SELECT * FROM PRODUCTOS WHERE CÓDIGOARTÍCULO = ?";
		
		// CREAR CONSULTA PREPARADA
		    myStatement = myConnection.prepareStatement(sql);
		
		// ESTABLECER LOS PARAMETROS
			myStatement.setString(1, codigoArt);
		
		// EJECUTA CONSULTA
			rs = myStatement.executeQuery();
		
		// OBTENER DATOS DE RESPUESTA
			if(rs.next()) {
				
				String c_Art = rs.getString("CÓDIGOARTÍCULO");
				String section = rs.getString("SECCIÓN");
				String nArt = rs.getString("NOMBREARTÍCULO");
				double price = rs.getDouble("PRECIO");
				String oCountry = rs.getString("PAÍSDEORIGEN");
				Date date = rs.getDate("FECHA");
				boolean imported = rs.getBoolean("IMPORTADO");
				
				myProduct = new Products(c_Art, section, nArt, price, imported, oCountry, date);
				
			}else {
				throw new Exception("Product not found. ID product: " + codigoArt);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return myProduct;
	}
	
	public void updateProduct(Products myProductUpdated) throws Exception {
		// TODO Auto-generated method stub
		Connection myConnection = null;
		java.sql.PreparedStatement myStatement = null;
		myProductUpdated.setImported(false);
		System.out.println("PRODUCT EN EL MODELO");
		System.out.println(myProductUpdated.getPrice());
		System.out.println(myProductUpdated);
		
		try {
		 // ESTABLECER CONEXIÓN CON BBDD
			myConnection = origenData.getConnection();
		
		// CREAR SENTENCIA SQL
			String sql = "UPDATE PRODUCTOS SET SECCIÓN = ?, NOMBREARTÍCULO = ?, PRECIO = ?, FECHA = ?, IMPORTADO = ?, PAÍSDEORIGEN = ? WHERE CÓDIGOARTÍCULO = ?";
		
		// CREAR PREPARED STATEMENT 
			myStatement = myConnection.prepareStatement(sql);
		
		// ESTABLECER PARAMETROS
			myStatement.setString(7, myProductUpdated.getcArt());
			myStatement.setString(1, myProductUpdated.getSection());
			myStatement.setString(2, myProductUpdated.getnArt());
			myStatement.setDouble(3, myProductUpdated.getPrice());
			
			java.util.Date utilDate = myProductUpdated.getDate();
			java.sql.Date convertDate = new java.sql.Date(utilDate.getTime());
			
			myStatement.setDate(4, convertDate);
			myStatement.setBoolean(5, myProductUpdated.getImported());
			myStatement.setString(6, myProductUpdated.getoCountry());
			
		
		// EJECUTAR SENTENCIA SQL
			myStatement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			myConnection.close();
		}
	}
	
	public void deleteProduct(String cArt) {
		// TODO Auto-generated method stub
		Connection myConnection = null;
		java.sql.PreparedStatement myStatement = null;
		
		try {
		// ESTABLECER CONEXIÓN CON BBDD
			myConnection = origenData.getConnection();
		
		// CREAR SENTENCIA SQL
			String sql = "DELETE FROM PRODUCTOS WHERE CÓDIGOARTÍCULO = ?";
		
		// CREAR PREPARED STATEMENT 
			myStatement = myConnection.prepareStatement(sql);
		
		// ESTABLECER PARAMETROS
			myStatement.setString(1, cArt);
		
		// EJECUTAR SENTENCIA SQL
			myStatement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	// ---------- ---------- ---------- VARIABLES ---------- ---------- ----------
	
	DataSource origenData;

}
