<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert a product</title>
</head>
<body>
<div>

<form name="forml" method = "get" action = "ProductsController">
<input type = "hidden" name = "instrction" value = "insertBBDD" />

<input type="text" name= 'cArt' placeholder = "cArt.." /> 
 <br /> <br />

 <input type="text" name= 'Section' placeholder = "section.." /> 
 <br /> <br />
 
 <input type="text" name= 'Name' placeholder = "name.." />
 <br /> <br />
 
 <input type="text" name= 'Date' placeholder = "Date.." />
 <br /> <br />
 
 <input type="text" name= 'Price' placeholder = "price.." />
 <br /> <br />
 
 <input type="text" name= 'OriginCountry' placeholder = "origin country.." />
 <br /> <br />
 
 <input type = "submit" value = "Send"/>
 <input type = "reset" value = "restart"/>

</form>

</div>
</body>
</html>