package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	        // Assuming the Estudante class has a getDisciplinas method that returns a List<Disciplina>
	        List<Disciplina> disciplinas = estudante.getDisciplinas();

	        // Insert a separate row for each associated Disciplina
	        for (Disciplina disciplina : disciplinas) {
	            statement.setInt(3, disciplina.getId()); // Assuming you have a getId method in Disciplina

	            // Execute the SQL INSERT statement for each Disciplina
	            statement.executeUpdate();
	        }

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

	@Override
    public Disciplina insertDisciplina(Disciplina disciplina) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO disciplinas (nome, nivel, media_aprovacao, conceito) VALUES (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            statement.setString(1, disciplina.getNome());
            statement.setString(2, disciplina.getNivel());
            statement.setFloat(3, disciplina.getMediaAprovacao());
            statement.setString(4, disciplina.getConceito());

            // Execute the SQL INSERT statement and get the generated ID
            int disciplinaId = -1;
            if (statement.executeUpdate() > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    disciplinaId = generatedKeys.getInt(1);
                }
            }

            if (disciplinaId != -1) {
                System.out.println("Disciplina inserida com sucesso. ID: " + disciplinaId);
                // Update the Disciplina object with the generated ID
                disciplina.setId(disciplinaId);
                return disciplina;
            } else {
                throw new RuntimeException("Erro ao inserir disciplina.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir disciplina: " + e.getMessage());
        }
    }


}