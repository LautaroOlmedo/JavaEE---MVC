package com.lautaroOlmedo.webProducts;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

/**
 * Servlet implementation class ProductsController
 */
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ESTABLECER EL DATASOURCE
		@Resource(name = "jdbc/WebProducts")
		private DataSource myPool;
		
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			super.init();
			try {
				productsModel = new ProductsModel(myPool);
			}catch(Exception e) {
				e.printStackTrace();
				throw new ServletException("ERROR AL INTENTAR CONECTAR CON EL MODELO: ", e);
			}
		}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// LEER PARAMETRO DEL FORM 
		String command = request.getParameter("instrction");
		// SI NO SE ENVÍA PARAMETRO EJECUTAR LISTAR PRODUCTOS
		
		if(command == null) command = "list";
		
		// REDIRIGIR EL FLUJO DE EJECUCIÓN AL MÉTODO ADECUADO
		switch (command) {
		case "list": 
			allProducts(request, response);
			break;
		
		case "insertBBDD":
			addProduct(request, response);
			break;
			
		case "load":
			loadProduct(request, response);
			break;
			
		case "updateBBDD":
			updateProduct(request, response);
			break;
			
		case "delete":
			deleteProduct(request, response);
			break;
			
		
		default:
			allProducts(request, response);
			break;
		}
	
	}
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// CAPTURAR EL cART
		String cArt = request.getParameter("CArticulo");
		
		// BORRAR PRODUCT DE LA BBDD
		productsModel.deleteProduct(cArt);
		
		// VOLVER AL LISTADO DE PRODUCTOS
		allProducts(request, response);
		
	}
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// LEER DATOS DEL FORMLULARIO
		String cArt = request.getParameter("cArt");
		String section = request.getParameter("Section");
		String name = request.getParameter("Name");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(request.getParameter("Date"));
			System.out.println("MI DATE");
			System.out.println(date);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
	
			e.printStackTrace();
		} 
		double price = Double.parseDouble(request.getParameter("Price")); 
		//String imported = request.getParameter("imported");
		String oCountry = request.getParameter("OriginCountry");
		boolean imported = false;
		
		// CREAR UN PRDUCT CON LA INFO DEL FORM
		Products myProductUpdated = new Products(cArt, name, section, price, imported, oCountry, date);
		System.out.println(myProductUpdated);
		
		// ACTUALIZAR LA BBDD CON LA INF
		try {
			productsModel.updateProduct(myProductUpdated);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// VOLVER AL LISTADO CON LA INFO ACTUALIZADA 
		allProducts(request, response);
		
	}
	private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		try {
		
		// LEER EL CART DEL PRODUCT QUE VIENE DEL LISTADO
		String cArt = request.getParameter("CArticulo");
		
		
		// ENVIAR CART A MODELO 
		Products myProduct = productsModel.getProduct(cArt);
		
		// ESTABLECER EL ATRIBUTO CORRESPONDIENTE AL CART
		request.setAttribute("updatedProduct", myProduct);
		
		
		// ENVIAR PRODUCT AL FORMULARIO DE ACTUALIZAR (JSP)
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateProduct.jsp");
			dispatcher.forward(request, response);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// LEER LA INFORMACIÓN DEL PRODUCTO QUE VIENE DEL FORM
		String cArt = request.getParameter("cArt");
		String section = request.getParameter("Section");
		String name = request.getParameter("Name");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(request.getParameter("Date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		double price = Double.parseDouble(request.getParameter("Price")); 
		//String imported = request.getParameter("imported");
		String oCountry = request.getParameter("OriginCountry");
					
		// CREAR UN OBJ DE TIPO PRODUCT
		Products myProduct = new Products(cArt, section, name, price, false, oCountry, date);
		
		// ENVIAR OBJ AL MODELO
		productsModel.addProductModel(myProduct);
		
		// VOLVER AL LISTADO DE PRODUCTS
		try {
			allProducts(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	private void allProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// OBTENER LA LISTA DE PRODUCTOS DEL MODELO
				List<Products> products;
				try {
					
					// OBTENER LA LISTA DE PRODUCTOS DEL MODELO
					products = productsModel.getProducts();
					
					// AGREGAR ESA LISTA AL REQUEST
					request.setAttribute("productsList", products);
					
					// ENVIAR ESE REQUEST A LA PÁGINA JSP
					RequestDispatcher myDistpatcher = request.getRequestDispatcher("/ProductsList.jsp");
					myDistpatcher.forward(request, response);
					
				}catch(Exception e){
					e.printStackTrace();
					throw new ServletException("ERROR AL INTENTAR TRAER LA INFORMACIÓN: ", e);
				}
	
	}
	private ProductsModel productsModel;

}
