package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Disciplina;
import modelo.Estudante;

public class DaoOracle implements iDao {
	private ConnectionFactory connectionFactory;
	
	public DaoOracle(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

	@Override
	public void insertEstudante() {
		System.out.println(" Inseriu pelo DaoOracle");
	}

	@Override
	public List<Estudante> getEstudantes() {
		 List<Estudante> estudantes = new ArrayList<>();
		 try (Connection connection = connectionFactory.getConnection();
	             PreparedStatement statement = connection.prepareStatement(
	            		 "SELECT e.id, e.nome, d.id as idDisciplina ,d.nome as Disciplina ,d.curso, d.media_aprovacao FROM estudantes e " +
	                     "INNER JOIN disciplinas d ON e.id_disciplina = d.id");
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                int estudanteId = resultSet.getInt("id");
	                int disciplinaId = resultSet.getInt("idDisciplina");
	                String estudanteNome = resultSet.getString("nome");
	                String disciplinaNome = resultSet.getString("Disciplina");
	                String disciplinaCurso = resultSet.getString("curso");
	                float mediaAprovação = resultSet.getFloat("media_aprovacao");
	                

	                Estudante estudante = new Estudante(estudanteId, estudanteNome, new Disciplina(disciplinaId, disciplinaNome, disciplinaCurso, mediaAprovação));
	                estudantes.add(estudante);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return estudantes;
	}
	



}
