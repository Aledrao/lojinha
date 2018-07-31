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
	<form:form action="/fabricante/adicionar" method="post" commandName="fabricante">
		<h1>Cadastrar Fabricante</h1>
		<div class="row">
			<div class="col-md-1">
				<label class="col-sm-2 col-form-label" for="nome">Adicionar:</label>
			</div>
			<div class="col-md-4">		
				<form:input path="nome" id="nome" cssClass="form-control" />
			</div>
			<div class="col-md-3">
				<form:errors path="nome" cssStyle="color:red"></form:errors>
			</div>
		</div>
		<div>
			<button type="submit" class="btn btn-success">Salvar</button>
		</div>
	</form:form>	
	
	<script src="/jquery/jquery-3.2.1.min.js"></script>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>