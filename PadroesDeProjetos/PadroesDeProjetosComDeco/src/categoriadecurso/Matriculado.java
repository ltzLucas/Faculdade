package categoriadecurso;

public abstract class Matriculado implements Matricula {
	protected Matricula matricula;
	
	public Matriculado(Matricula matricula) {
		this.matricula = matricula;
	}
}