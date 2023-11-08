package jdbc;

import dao.DaoOracle;
import dao.DaoMySQL;
import dao.iDao;



/*
 * Aqui é a lógica para mudar as instancias de Oracle para MySQL
 */

public class DataBase {
	private ConnectionFactory dataBaseFactory;
	public iDao dao = null;
	
	public DataBase(ConnectionFactory dataBaseFactory) {
		this.dataBaseFactory = dataBaseFactory;
		
		if(this.dataBaseFactory instanceof OracleConnectionFactory) {
			this.dao = new DaoOracle(dataBaseFactory);
		}else if(this.dataBaseFactory instanceof MySQLConnectionFactory) {
			this.dao = new DaoMySQL(dataBaseFactory);
		}
		
	}
	
	public ConnectionFactory getConnectionFacotory() {
		return this.dataBaseFactory;
	}
	

}