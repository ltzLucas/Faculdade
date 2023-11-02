package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionFactory implements ConnectionFactory {
	
	/*
	 * Aqui é a conexao ao banco de dados: Só mudar Onde esta o meubanco para seu schema do mysql onde root usuario e o 1234 para sua senha
	 */

	@Override
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Usando MySQL");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/meubanco","root","1234");
		}catch(SQLException  | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}


}
