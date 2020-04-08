package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;

public class LoginDAO {

	private Connection connection;

	public LoginDAO() {
		connection = SingleConnection.getConnection();
	}

	public boolean validateLogin(String login, String senha) {
		String sql = "select * from usuario";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				
				String loginV = resultSet.getString("login");
				String senhaV = resultSet.getString("senha");
				
				if (login.equals(loginV) && senha.equals(senhaV)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
