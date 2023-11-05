package modelo;

public class Disciplina {
	
	private int id;
	private String nome;
	private String nivel;
	private Float mediaAprovacao;
	private String conceito;
	
	public Disciplina(int id, String nome, String nivel, Float mediaAprovacao, String conceito) {
		this.id = id;
		this.nome = nome;
		this.nivel = nivel;
		this.mediaAprovacao = mediaAprovacao;
		this.conceito = conceito;
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
	
	public String getConceito() {
		return conceito;
	}

	@Override
	public String toString() {
		return " Disciplina [id: " + id + ", nome: " + nome + ", nivel: " + nivel + 
				", mediaAprovacao: " + mediaAprovacao + ", conceito: " + conceito + "]";
	}

}
