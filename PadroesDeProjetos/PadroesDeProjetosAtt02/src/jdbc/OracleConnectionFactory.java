package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionFactory implements ConnectionFactory {
	/*
	 * Aqui é a conexao ao banco de dados: Só mudar Onde esta o C## para seu usuario e o 123 para sua senha da oracle
	 */

	@Override
	public Connection getConnection() {
		try {
//			System.out.println("Usando Oracle");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","C##","123");
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}