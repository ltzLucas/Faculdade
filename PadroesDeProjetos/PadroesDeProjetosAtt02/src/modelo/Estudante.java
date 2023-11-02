package modelo;

public class Estudante {
	/* Piazada seguinte aqui eu criei algo simples mais pra conseguir fazer a conexao ao banco n levem em consideração
	 * As Duas classes Disciplina e Estudantes
	 * 
	 * Aqui vcs colocam os atributos que vcs acham q vao precisar para fazer a lógica da Estudante
	 * 
	 * 
	 * 
	 */
	private int id;
	private String nome;
	private Disciplina disciplina;
	
	public Estudante(int id, String nome, Disciplina disciplina) {
		this.id = id;
		this.nome = nome;
		this.disciplina = disciplina;
	}

	public String getNome() {
		return nome;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}

	@Override
	public String toString() {
		return "Estudante [id: " + id + ", nome: " + nome + ", disciplina: " + disciplina + "]";
	}
	
	
	

	
	
}
