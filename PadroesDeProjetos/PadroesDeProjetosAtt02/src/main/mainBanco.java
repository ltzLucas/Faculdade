package main;

import java.util.List;

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
    	
		ConnectionFactory dataBaseFacotory = new MySQLConnectionFactory();
		DataBase dataBase = new DataBase(dataBaseFacotory);
		
		dataBase.dao.insertEstudante();
		
		// Feito esse codigo apenas para teste
		
        Disciplina matematica = new Disciplina(1, "Matemática", "Técnico", 7.0f, "A");
        Disciplina fisica = new Disciplina(2, "Física", "Técnico", 6.5f, "B");
        Disciplina historia = new Disciplina(3, "História", "Bacharelado", 5.5f, "C");
        Disciplina literatura = new Disciplina(4, "Literatura", "Bacharelado", 6.8f, "A");
        Disciplina pesquisa = new Disciplina(5, "Pesquisa", "Mestrado", 7.2f, "B");

        Estudante estudanteTecnico = new Estudante(1, "João", "Técnico", List.of(matematica, fisica));
        Estudante estudanteBacharelado = new Estudante(2, "Maria", "Bacharelado", List.of(historia, literatura));
        Estudante estudanteMestrado = new Estudante(3, "Carlos", "Mestrado", List.of(pesquisa));

        Curso cursoTecnico = new CursoTecnico();
        Curso cursoBacharelado = new CursoBacharelado();
        Curso cursoMestrado = new CursoMestrado();

        boolean aprovadoTecnico = cursoTecnico.verificarAprovacao(estudanteTecnico);
        boolean aprovadoBacharelado = cursoBacharelado.verificarAprovacao(estudanteBacharelado);
        boolean aprovadoMestrado = cursoMestrado.verificarAprovacao(estudanteMestrado);

        System.out.println("Estudante de Técnico está aprovado: " + aprovadoTecnico);
        System.out.println("Estudante de Bacharelado está aprovado: " + aprovadoBacharelado);
        System.out.println("Estudante de Mestrado está aprovado: " + aprovadoMestrado);
    }
}
