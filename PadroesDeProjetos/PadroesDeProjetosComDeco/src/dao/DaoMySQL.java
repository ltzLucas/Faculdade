package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.ConnectionFactory;
import modelo.Disciplina;
import modelo.Estudante;

public class DaoMySQL implements iDao  {
	
private ConnectionFactory connectionFactory;
	
	public DaoMySQL(ConnectionFactory connectionFactory) {
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
	    Map<String, Map<String, Estudante>> groupedEstudantes = new HashMap<>();

	    try (Connection connection = connectionFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(
	                 "SELECT e.id, e.nome, e.curso, d.id as idDisciplina, d.nome as Disciplina, d.nivel, d.conceito, d.media_aprovacao " +
	                 "FROM estudantes e " +
	                 "INNER JOIN disciplinas d ON e.id_disciplina = d.id");
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            String estudanteNome = resultSet.getString("nome");
	            String estudanteCurso = resultSet.getString("curso");

	            int disciplinaId = resultSet.getInt("idDisciplina");
	            String disciplinaNome = resultSet.getString("Disciplina");
	            String disciplinaNivel = resultSet.getString("nivel");
	            String disciplinaConceito = resultSet.getString("conceito");
	            float mediaAprovacao = resultSet.getFloat("media_aprovacao");

	            List<Disciplina> disciplinasList = new ArrayList<>();
	            disciplinasList.add(new Disciplina(disciplinaId, disciplinaNome, disciplinaNivel, mediaAprovacao, disciplinaConceito));

	            Estudante estudante = new Estudante(0, estudanteNome, estudanteCurso, disciplinasList);

	            // Check if the Map for the current nome already exists
	            if (!groupedEstudantes.containsKey(estudanteNome)) {
	                groupedEstudantes.put(estudanteNome, new HashMap<>());
	            }

	            // Add the Estudante to the Map for the current nome/curso
	            groupedEstudantes.get(estudanteNome).put(estudanteCurso, estudante);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

	    // Flatten the grouped Estudantes Map and return the result as a List
	    List<Estudante> result = new ArrayList<>();
	    groupedEstudantes.values().forEach(cursoMap -> result.addAll(cursoMap.values()));
	    return result;
	}

	@Override
	public Disciplina insertDisciplina(Disciplina disciplina) {
	    try (Connection connection = connectionFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(
	                 "INSERT INTO disciplinas (nome, nivel, media_aprovacao, conceito) VALUES (?, ?, ?, ?)",
	                 Statement.RETURN_GENERATED_KEYS)) {

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