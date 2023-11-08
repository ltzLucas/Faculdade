package modelo;

import java.util.List;

public class Estudante {

    private int id;
    private String nome;
    private String curso;
    private List<Disciplina> disciplinas;
    
    public Estudante(int id, String nome, String curso, List<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return nome;
    }
    
    public String getCurso() {
    	return curso;
    }
    
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @Override
    public String toString() {
        return "Estudante [id: " + id + ", nome: " + nome + ", curso: " + curso + ", disciplinas: " + disciplinas + "]";
    }
}
