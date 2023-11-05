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
    public void insertEstudante(Estudante estudante) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO estudantes (nome, curso, id_disciplina) VALUES (?, ?, ?)")) {

            // Set the values for the prepared statement
            statement.setString(1, estudante.getNome());
            statement.setString(2, estudante.getCurso());
            statement.setInt(3, estudante.getIdDisciplina());

            // Execute the SQL INSERT statement
            statement.executeUpdate();

            System.out.println("Estudante inserido com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir estudante: " + e.getMessage());
        }
    }

	@Override
	public List<Estudante> getEstudantes() {
		 List<Estudante> estudantes = new ArrayList<>();
		 try (Connection connection = connectionFactory.getConnection();
		         PreparedStatement statement = connection.prepareStatement(
		                 "SELECT e.id, d.id as idDisciplina, e.nome, d.nome as Disciplina, d.nivel, e.curso, d.conceito, d.media_aprovacao" +
		                         "FROM estudantes e " +
		                         "INNER JOIN disciplinas d ON e.id_disciplina = d.id");
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                int estudanteId = resultSet.getInt("id");
	                int disciplinaId = resultSet.getInt("idDisciplina");
	                String estudanteNome = resultSet.getString("nome");
	                String disciplinaNome = resultSet.getString("Disciplina");
	                String disciplinaNivel = resultSet.getString("nivel");
	                String estudanteCurso = resultSet.getString("curso");
	                String disciplinaConceito = resultSet.getString("conceito");
	                float mediaAprovação = resultSet.getFloat("media_aprovacao");
	                
	                Estudante estudante = new Estudante(estudanteId, estudanteNome, estudanteCurso, (List<Disciplina>) new Disciplina(disciplinaId, disciplinaNome, disciplinaNivel, mediaAprovação, disciplinaConceito));
	                estudantes.add(estudante);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return estudantes;
	}
	



}