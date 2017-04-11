<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Visualizar Colaborador</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
		<c:import url="Menu.jsp"/>
        
        <div id="main" class="container">
            <h3 class="page-header">Visualizar Avaliador #${avaliador.login}</h3>
            <div class="row">
                <div class="col-md-12">
                    <p><strong>Nome</strong>
                    </p>
                    <p>
                        ${avaliador.nome}
                    </p>
                </div>
            </div>
            <div class="row">
                
                <div class="col-md-6">
                    <p><strong>Setor</strong>
                    </p>
                    <p>
                        ${avaliador.setor}
                    </p>
                </div>
                <div class="col-md-6">
                    <p><strong>Senha</strong>
                    </p>
                    <p>
                        ${avaliador.senha}
                    </p>
                </div>
            </div>
            <hr />
            <div id="actions" class="row">
                <div class="col-md-12">
                    <a href="AvaliadorCadas.jsp" class="btn btn-default">Voltar</a>
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
</body>

</html>