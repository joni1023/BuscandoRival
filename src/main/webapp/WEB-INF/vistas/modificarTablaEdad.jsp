<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<title>Modificar los valores de la valoración de la tabla edad</title>
</head>
<body class="container">

	<h1>Modificar los valores de la valoración de la tabla edad</h1>
	<table class="table table-bordered text-center">
		<thead >
		    <tr>
		      <th scope="col">Menor de 20</th>
		      <th scope="col">20 a 25</th>
		      <th scope="col">26 a 30</th>
		      <th scope="col">31 a 35</th>
		      <th scope="col">Mayor de 35</th>
		    </tr>
		</thead>
		<tbody>
		    <tr>
		
		<form:form action="modificarEdadT" method="POST" modelAttribute="valoracionEdad">
		<form:input path="id" type="hidden" value="${valorEdad.id}" />
		<th scope="col"><form:input path="menor20" type="text" value="${valorEdad.menor20}"/></th>
		<th scope="col"><form:input path="de20a25" type="text" value="${valorEdad.de20a25}"/></th>
		<th scope="col"><form:input path="de26a30" type="text" value="${valorEdad.de26a30}"/></th>
		<th scope="col"><form:input path="de31a35" type="text" value="${valorEdad.de31a35}"/></th>
		<th scope="col"><form:input path="mayor35" type="text" value="${valorEdad.mayor35}"/></th>
		 	</tr>
		</tbody>
		</table>
		<button type="submit" class="btn btn-info">Guardar cambios</button>
		</form:form>
	
	
</body>
</html>