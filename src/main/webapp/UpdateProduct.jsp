<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product</title>
</head>
<body>
<div>

<form name="forml" method = "get" action = "ProductsController">
<input type = "hidden" name = "instrction" value = "updateBBDD" />

<input type = "hidden" name = "cArt" value = "${updatedProduct.cArt}" />


 <input type="text" name= 'Section' value = "${updatedProduct.section}" /> 
 <br /> <br />
 
 <input type="text" name= 'Name' value = "${updatedProduct.nArt}"/>
 <br /> <br />
 
 <input type="text" name= 'Date' value = "${updatedProduct.date}" />
 <br /> <br />
 
 <input type="text" name= 'Price' value = "${updatedProduct.price}" />
 <br /> <br />
 
 <input type="text" name= 'OriginCountry'  value = "${updatedProduct.oCountry}" />
 <br /> <br />
 
 <input type = "submit" value = "Send"/>
 <input type = "reset" value = "restart"/>

</form>

</div>
</body>
</html>