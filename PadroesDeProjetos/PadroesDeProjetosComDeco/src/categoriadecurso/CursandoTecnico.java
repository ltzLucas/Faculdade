package categoriadecurso;

public class CursandoTecnico extends Matriculado{
	
	public CursandoTecnico(Matricula matricula) {
		super(matricula);	
	}
	@Override
	public String matricula() {
		// TODO Auto-generated method stub
		return matricula.matricula()+ "\nAluno matriculado no tecnico";
	}
}