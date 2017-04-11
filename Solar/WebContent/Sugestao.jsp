<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.FuncaoSugestao" %>
<!DOCTYPE">
<html lang="pt-br">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sugestao</title>
</head>
<body>
<c:import url="Menu.jsp"/>

	<%FuncaoSugestao sugestao = (FuncaoSugestao)request.getAttribute("sugestao"); %>
	Id: ${sugestao.id}<br>
	Setorid: ${sugestao.setorid}<br>
	Titulo: ${sugestao.titulo}<br>
	Setor: ${sugestao.setor}<br>
	Colaborador: ${sugestao.colab}<br>
	Avaliador: ${sugestao.aval}<br>
	Descricao: ${sugestao.descricao}<br>
	Aprovado: ${sugestao.aprovado}<br>
</body>
</html>