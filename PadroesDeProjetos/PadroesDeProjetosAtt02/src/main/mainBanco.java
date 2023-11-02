package main;

import java.sql.SQLException;

import dao.DaoOracle;
import dao.DaoMySQL;
import dao.iDao;
import jdbc.ConnectionFactory;
import jdbc.DataBase;
import jdbc.MySQLConnectionFactory;
import jdbc.OracleConnectionFactory;
import modelo.Disciplina;
import modelo.Estudante;

public class mainBanco {
	/*
	 *	Para utilizar o dao basta seguir esse exemplo 
	 *
	 *
	 *	ConnectionFactory dataBaseFacotory = new MySQLConnectionFactory(); ->  Nessa linha escolhe entre MySQLConnectionFactory ou OracleConnectionFactory
	 *	DataBase dataBase = new DataBase(dataBaseFacotory);
	 *	dataBase.dao.insertEstudante();
	 * 
	 */

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ConnectionFactory dataBaseFacotory = new MySQLConnectionFactory();
		DataBase dataBase = new DataBase(dataBaseFacotory);
		
		dataBase.dao.insertEstudante();

		
		
	}
}
