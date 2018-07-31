<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css" />
	<title>Insert title here</title>
</head>
<body>
	<form:form action="/fabricante/excluir/{codigo}" method="delete" commandName="fabricante">
		<h1>Excluir fabricante</h1>
	
		<div class="row">
			<div class="col-md-1">
				<label class="col-md-2 col-form-label" for="codigo">Código:</label>
			</div>
			<div class="col-md-4">
				<form:input path="codigo" id="codigo" readonly="true" class="form-control"/>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-1">
				<label class="col-md-2 col-form-label" for="nome">Fabricante:</label>
			</div>
			<div class="col-md-4">
				<form:input path="nome" id="nome" readonly="true" class="form-control"/>
			</div>
		</div>
		
		<div>
			<button value="submit" class="btn btn-success">Deletar</button>
		</div>

	</form:form>
		
	<script src="/jquery/jquery-3.2.1.min.js"></script>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>