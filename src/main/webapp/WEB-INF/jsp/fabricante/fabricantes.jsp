<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css" />
	<script defer src="/fontawesome/svg-with-js/js/fontawesome-all.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<form id="formFabricantes">
	<jsp:useBean id="link" class="br.com.asas.lojinha.controller.FabricanteController" />
	<h1>Fabricantes</h1>
	
	<c:if test="${not empty sucesso}">
		<div class="alert alert-success alert-dismissible fade show" role="alert">
			${sucesso}
			<button type="button" class="close" data-dismiss="alert" arial-label="Close">
				<span arial-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>
	<br />
	
	<div class="row">
		<div class="col-md-12">
			<a href="${pageContext.request.contextPath}/fabricante/adicionar">Adicionar Fabricante</a>
		</div>
	</div>
	
	<table class="table" id="tbFabricantes">
		<thead>
			<tr>
				<th scope="col">Código</th>
				<th scope="col">Fabricante</th>
				<th scope="col">Alterar</th>
				<th scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fabricantes}" var="fabricante">
				<tr>
					<td>${fabricante.codigo}</td>
					<td>${fabricante.nome}</td>
					<td class="center-align">						
						<a href="${pageContext.request.contextPath}/fabricante/atualizar/${fabricante.codigo}">
							<i class="fas fa-pencil-alt"></i>
						</a>
					</td>
					<td class="center-align">
						<a href="${pageContext.request.contextPath}/fabricante/excluir/${fabricante.codigo}">
							<i class="fas fa-eraser"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>	
	<script src="/jquery/jquery-3.3.1.min.js"></script>
	<script src="/bootstrap/js/bootstrap.min.js"></script>	
	</form>
</body>
</html>