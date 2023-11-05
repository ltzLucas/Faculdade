package dao;

import java.util.List;

import modelo.Estudante;

public interface iDao {
	
	/*
	 * Aqui vc cria as assinaturas para os metodos DAO, ou seja cria aqui e implementa na DaoOracle e na DaoMySQL
	 */
	void insertEstudante();
	public List<Estudante> getEstudantes();
}