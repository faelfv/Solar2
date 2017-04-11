<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.FuncaoColaborador" %>
<!DOCTYPE">
<html lang="pt-br">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Recuperar Senha</title>
</head>
<body>
<c:import url="Menu.jsp"/>

	<%FuncaoColaborador recuperarsenha = (FuncaoColaborador)request.getAttribute("recuperarsenha"); %>
	Login: ${recuperarsenha.login}<br>
	Senha: ${recuperarsenha.senha}<br>
	Nova Senha: ${recuperarsenha.senhax}<br>
</body>
</html>