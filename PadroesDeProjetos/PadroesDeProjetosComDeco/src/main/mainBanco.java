package main;



import java.util.List;
import categoriadecurso.*;

import dao.DaoOracle;
import dao.DaoMySQL;
import dao.iDao;
import jdbc.ConnectionFactory;
import jdbc.DataBase;
import jdbc.MySQLConnectionFactory;
import jdbc.OracleConnectionFactory;
import modelo.Disciplina;
import modelo.Estudante;
import modelo.CursoTecnico;
import modelo.Curso;
import modelo.CursoBacharelado;
import modelo.CursoMestrado;

public class mainBanco {

    public static void main(String[] args) {
    	
		ConnectionFactory dataBaseFactory = new MySQLConnectionFactory();
//		ConnectionFactory dataBaseFactory = new OracleConnectionFactory();
		DataBase dataBase = new DataBase(dataBaseFactory);
		
		
		// Construção e persistência dos dados
		
        Disciplina matematica = new Disciplina(1, "Matemática", "Técnico", 7.0f, "A");
        Disciplina fisica = 	new Disciplina(2, "Física", "Técnico", 6.5f, "B");
        Disciplina historia = 	new Disciplina(3, "História", "Bacharelado", 5.5f, "C");
        Disciplina literatura = new Disciplina(4, "Literatura", "Bacharelado", 6.8f, "A");
        Disciplina pesquisa = 	new Disciplina(5, "Pesquisa", "Mestrado", 7.2f, "B");
        matematica = 	dataBase.dao.insertDisciplina(matematica);
        fisica = 		dataBase.dao.insertDisciplina(fisica);
        historia = 		dataBase.dao.insertDisciplina(historia);
        literatura = 	dataBase.dao.insertDisciplina(literatura);
        pesquisa = 		dataBase.dao.insertDisciplina(pesquisa);

        Estudante estudanteTecnico = 		new Estudante(1, "João", "Técnico", List.of(matematica, fisica));
        Estudante estudanteBacharelado = 	new Estudante(2, "Maria", "Bacharelado", List.of(historia, literatura));
        Estudante estudanteMestrado = 		new Estudante(3, "Carlos", "Mestrado", List.of(pesquisa));
		dataBase.dao.insertEstudante(estudanteTecnico);
		dataBase.dao.insertEstudante(estudanteBacharelado);
		dataBase.dao.insertEstudante(estudanteMestrado);

        // Obtenção dos dados via banco de dados
        List<Estudante> estudantes = dataBase.dao.getEstudantes();

        Curso cursoTecnico = new CursoTecnico();
        Curso cursoBacharelado = new CursoBacharelado();
        Curso cursoMestrado = new CursoMestrado();

        for (Estudante estudante : estudantes) {
            if ("Técnico".equals(estudante.getCurso())) {
                boolean aprovadoTecnico = cursoTecnico.verificarAprovacao(estudante);
                System.out.println("Estudante " + estudante.getNome() + " de Técnico está aprovado: " + aprovadoTecnico);
            } else if ("Bacharelado".equals(estudante.getCurso())) {
                boolean aprovadoBacharelado = cursoBacharelado.verificarAprovacao(estudante);
                System.out.println("Estudante " + estudante.getNome() + " de Bacharelado está aprovado: " + aprovadoBacharelado);
            } else if ("Mestrado".equals(estudante.getCurso())) {
                boolean aprovadoMestrado = cursoMestrado.verificarAprovacao(estudante);
                System.out.println("Estudante " + estudante.getNome() + " de Mestrado está aprovado: " + aprovadoMestrado);
            }
        }
        //decoretor
        System.out.println("Decorator");
        Matricula matricula1 = new cursobas();
        System.out.println(matricula1.matricula());
        matricula1 = new CursandoTecnico(matricula1); 
        System.out.println(matricula1.matricula());
        matricula1 = new CursandoBAcharelado(matricula1);
        System.out.println(matricula1.matricula());
        matricula1 = new CursandoMestrado(matricula1);
        System.out.println(matricula1.matricula());
    }
}
