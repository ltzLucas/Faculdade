package modelo;

public class Disciplina {
	
	/* Piazada seguinte aqui eu criei algo simples mais pra conseguir fazer a conexao ao banco n levem em consideração
	 * As Duas classes Disciplina e Estudantes
	 * 
	 * Aqui vcs colocam os atributos que vcs acham q vao precisar para fazer a lógica da Disciplina
	 * 
	 * 
	 * 
	 */
	
	private int id;
	private String nome;
	private String nivel;
	private Float mediaAprovacao;
	
	public Disciplina(int id, String nome, String nivel, Float mediaAprovacao) {
		this.id = id;
		this.nome = nome;
		this.nivel = nivel;
		this.mediaAprovacao = mediaAprovacao;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getNivel() {
		return nivel;
	}
	public Float getMediaAprovacao() {
		return mediaAprovacao;
	}

	@Override
	public String toString() {
		return " Disciplina [id: " + id + ", nome: " + nome + ", nivel: " + nivel + ", mediaAprovacao: " + mediaAprovacao
				+ "]";
	}

	
	
}
