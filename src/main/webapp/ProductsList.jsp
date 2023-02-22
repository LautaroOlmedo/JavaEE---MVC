<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>



<body>

<table>

<tr>

<td>Código Artículo</td>
<td>Sección</td>
<td>Nombre Artículo</td>
<td>Fecha</td>
<td>Precio</td>
<td>Importado</td>
<td>País Origen</td>
<td>Action</td>

</tr>

<c:forEach var = "tempProd" items = "${productsList}">

<!-- LINK PARA CADA PRODUCTO CON SU CAMPO CLAVE -->

<c:url var = "linkTemp" value = "ProductsController">

<c:param name = "instrction" value = "load"></c:param>
<c:param name = "CArticulo" value = "${tempProd.cArt}"></c:param>
</c:url>

<!-- LINK PARA ELIMINAR CADA PROD CON SU CAMPO CLAVE -->

<c:url var = "linkTempEliminar" value = "ProductsController">

<c:param name = "instrction" value = "delete"></c:param>
<c:param name = "CArticulo" value = "${tempProd.cArt}"></c:param>
</c:url>



<tr>

<td>${tempProd.cArt}</td>
<td>${tempProd.section}</td>
<td>${tempProd.nArt}</td>
<td>${tempProd.date}</td>
<td>${tempProd.price}</td>
<td>${temProd.imported}</td>
<td>${tempProd.oCountry}</td>
<td><a href = "${linkTemp}">Update</a>&nbsp;&nbsp;<a href = "${linkTempEliminar}">Eliminar</a></td>

</tr>

</c:forEach>

<div id = "contenedorButton">
<input type = "button" value = "Insert Register" onclick = "window.location.href = 'InsertProduct.jsp'"/> 
</div>

</table>

</body>
</html>