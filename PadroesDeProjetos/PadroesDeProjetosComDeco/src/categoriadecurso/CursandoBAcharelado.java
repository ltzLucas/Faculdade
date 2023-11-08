package categoriadecurso;

public class CursandoBAcharelado extends Matriculado{
	
	public CursandoBAcharelado(Matricula matricula) {
		super(matricula);	
	}
	@Override
	public String matricula() {
		// TODO Auto-generated method stub
		return matricula.matricula()+"\nAluno matriculado no Bacharelado";
	}
}
