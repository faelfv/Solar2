<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>cerveja.biz - Criar Colaborador</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    
  
</head>

<body>
    <!-- Barra superior com os menus de navegacao -->
	<c:import url="Menu.jsp"/>
    <!-- Container Principal -->
    <div id="main" class="container">
        <h3 class="page-header">Incluir Colaborador</h3>
        <!-- Formulario para inclusao de clientes -->
        <form action="ManterColaborador.do" method="post">
            <!-- area de campos do form -->
            <div class="row">
           
                <div class="form-group col-md-12">
                    <label for="nome">Nome</label>
                    <input type="text" class="form-control" name="nome" id="nome" required maxlength="100" placeholder="nome completo">
                </div>
            </div>
            <div class="row">
            
             <div class="form-group col-md-6">
                    <label for="cpf">CPF</label>
                    <input type="text" class="form-control" name="cpf" id="cpf" maxlength="14" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" placeholder="CPF no formato 999.999.999-99">
                </div>
                
                 <div class="form-group col-md-6">
                    <label for="tel">Telefone</label>
                    <input type="tel" class="form-control" name="tel" id="tel" maxlength="15" pattern="(?:\(\d{2}\)|\d{2})[- ]?\d{5}[- ]?\d{4}" placeholder="opcional; celular com ddd no formato (99) 99999-9999">
                </div>
            </div>
            
            <div class="row">
					 <div class="form-group col-md-6">
                    <label for="sexo">Sexo</label>
                    <input type="text" class="form-control" name="sexo" id="sexo" required maxlength="7" placeholder="F ou M">
                </div>
            
            </div>
            
            <div class="row">
               

                <div class="form-group col-md-6">
                    <label for="email">E-Mail</label>
                    <input type="email" class="form-control" name="email" id="email" required maxlength="60" placeholder="email obrigatório">
                </div>
                
                <div class="form-group col-md-6">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" name="login" id="login" required maxlength="10" placeholder="Login obrigatório">
                </div>
            </div>
            
             <div class="row">
               

                <div class="form-group col-md-6">
                    <label for="pass">Senha</label>
                    <input type="password" class="form-control" name="senha" id="senha" required maxlength="10" placeholder="Senha obrigatório">
                </div>
                
                <div class="form-group col-md-6">
                    <label for="pass">Confirmar Senha</label>
                    <input type="password" class="form-control" name="senha" id="senha" required maxlength="10" placeholder="Confirmar Senha obrigatório">
                </div>
            </div>
            <hr />
            <div id="actions" class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary" name="acao" value="Criar" >Salvar</button>
                    <a href="index.html" class="btn btn-default">Cancelar</a>
                </div>
            </div>
        </form>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>