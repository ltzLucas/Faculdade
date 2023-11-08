package categoriadecurso;

public class CursandoMestrado extends Matriculado{
	
	public CursandoMestrado(Matricula matricula) {
		super(matricula);	
	}
	@Override
	public String matricula() {
		// TODO Auto-generated method stub
		return matricula.matricula()+"\nAluno matriculado no Mestrado";
	}
}