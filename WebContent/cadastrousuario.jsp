<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
	<title>Cadastro</title>
	<meta charset="ISO-8859-1" lang="pt-BR">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="resources/css/table.css">
	<link rel="icon" type="icon/png" href="resources/images/icons/favicon.png">

<body>
	<form action="CadastroServlet" method="post" class="form-style-1" id="formAdd" onsubmit="return validateForm(this);">
		<h2>Cadastro</h2>
		<br>
		<h3 style="color: red;">${msg}</h3>
		<span>
			<label>ID:</label><input type="text" id="id" name="id" readonly="readonly" value="${user.id}">
		</span>
		<br>
		<br>
		<span>
			<label>Nome: </label><input type="text" id="nome" name="nome" placeholder="Insira o nome aqui"
				value="${user.nome}" />
		</span>
		<br>
		<br>
		<span>
			<label>Sobrenome: </label><input type="text" id="sobrenome" name="sobrenome"
				placeholder="Insira o sobrenome aqui" value="${user.sobrenome}" />
		</span>
		<br>
		<br>
		<span>
			<label>Nascimento: </label><input type="date" id="datanasc" name="datanasc" value="${user.dataNasc}" />
		</span>
		<br>
		<br>
		<span>
			<label>Cep: </label><input type="text" id="cep" name="cep" placeholder="00000-000" value="${user.cep}" />
		</span>
		<br>
		<br>
		<span>
			<label>Cidade: </label><input type="text" id="cidade" name="cidade" placeholder="Insira o nome da cidade" value="${user.cidade}" />
		</span>
		<br>
		<br>
		<span>
			<label>Rua: </label><input type="text" id="rua" name="rua" placeholder="Insira o nome da rua" value="${user.rua}" />
		</span>
		<br>
		<br>
		<span>
			<input type="submit" value="Salvar">
		</span>
		<span>
			<input type="submit" onclick="document.getElementById('formAdd').action = 'CadastroServlet?acao=reset'" value="Cancelar">
		</span>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados</caption>
			<thead>
				<tr>
					<th>#</th>
					<th>Nome</th>
					<th>Sobrenome</th>
					<th>Nascimento</th>
					<th>Cep</th>
					<th>Cidade</th>
					<th>Rua</th>
					<th>Excluir</th>
					<th>Editar</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="usuario" items="${lista}">
					<tr>
						<td>${usuario.id}</td>
						<td>${usuario.nome}</td>
						<td>${usuario.sobrenome}</td>
						<td>${usuario.dataNasc}</td>
						<td>${usuario.cep}</td>
						<td>${usuario.cidade}</td>
						<td>${usuario.rua}</td>
						<td><a href="CadastroServlet?acao=delete&usuario=${usuario.id}"><img alt="Excluir" title="Excluir" width="20px" height="20px" src="resources/images/icons/excluir.png"/></a></td>
						<td><a href="CadastroServlet?acao=alter&usuario=${usuario.id}"><img alt="Editar" title="Editar" width="20px" height="20px" src="resources/images/icons/editar.png"/></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="resources/js/validation.js"></script>
</body>

</html>