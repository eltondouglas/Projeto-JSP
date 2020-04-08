package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe resposavel por realizar a conexão com o banco de dados.
 * 
 * @author elton
 * @since 29/03/2020
 */
public class SingleConnection {

	private static Connection connection;

	/*
	 * Realiza a chamada ao metódo conectar toda vez que a classe SingleConnection
	 * for chamada.
	 */
	static {
		connect();
	}

	/**
	 * O metodo retorna uma conexão com o banco de dados.
	 * 
	 * @return {@link Connection}
	 */
	public static Connection getConnection() {
		return connection;
	}

	/**
	 * O metódo é responsavel por realiza uma conexão com o banco usando o padrão
	 * singleton.
	 */
	private static void connect() {

		if (connection == null) {

			String url = "jdbc:postgresql:javabd?autoReconnect=true";
			String user = "postgres";
			String password = "edm1508952010";
			
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
