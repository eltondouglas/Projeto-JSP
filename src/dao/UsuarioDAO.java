package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.Usuario;
import utils.DateUtil;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	public boolean incluir(Usuario usuario) {
		String sqlQuery = "insert into pessoa (nome, sobrenome, rua, cidade, cep, datanasc) values (?,?,?,?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getSobrenome());
			statement.setString(3, usuario.getRua());
			statement.setString(4, usuario.getCidade());
			statement.setString(5, usuario.getCep());
			statement.setString(6, DateUtil.dateToString(usuario.getDataNasc()));
			statement.execute();
			connection.commit();
			return true;
		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}// Fim do incluir

	public void atualizar(Usuario usuario) {
		String sqlQuery = "UPDATE pessoa" + " SET nome=?, sobrenome=?, cep=?, cidade=?, rua=?, datanasc=?"
				+ " WHERE id = " + usuario.getId();
		try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getSobrenome());
			statement.setString(3, usuario.getCep());
			statement.setString(4, usuario.getCidade());
			statement.setString(5, usuario.getRua());
			statement.setString(6, DateUtil.dateToString(usuario.getDataNasc()));
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}// Fim atualizar

	public Usuario buscar(String id) {
		String sqlQuery = "select * from pessoa where id = '" + id + "'";
		try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			ResultSet resultSet = statement.executeQuery();
			Usuario usuario = null;
			while (resultSet.next()) {
				usuario = new Usuario();
				usuario.setId(resultSet.getString("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSobrenome(resultSet.getString("sobrenome"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setRua(resultSet.getString("rua"));
				usuario.setDataNasc(DateUtil.stringToDate(resultSet.getString("datanasc"), DateUtil.PATTERN_BR));
			}
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}// Fim buscar

	public List<Usuario> listarTudo() throws SQLException, NullPointerException {
		String sqlQuery = "select * from pessoa";
		List<Usuario> pessoas = new ArrayList<>();

		try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			if (statement != null) {
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(resultSet.getString("id"));
					usuario.setNome(resultSet.getString("nome"));
					usuario.setSobrenome(resultSet.getString("sobrenome"));
					usuario.setCep(resultSet.getString("cep"));
					usuario.setCidade(resultSet.getString("cidade"));
					usuario.setRua(resultSet.getString("rua"));
					usuario.setDataNasc(DateUtil.stringToDate(resultSet.getString("datanasc"), DateUtil.PATTERN_BR));
					pessoas.add(usuario);
				}
			}
		}
		return pessoas;
	}// Fim listarTudo

	public void delete(String id) {
		String sqlQuery = "delete from pessoa where id = " + id + "";
		try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
			}
			e.printStackTrace();
		}
	} // Fim delete
	
	public boolean validarNome(String nome) {
		String sqlQuery = "select count(1) as qnt from pessoa where nome = '" + nome + "'";
		try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt("qnt") <=0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}// Fim validarNome

}// Fim UsuarioDAO
