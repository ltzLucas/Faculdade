package modelo;

public class CursoMestrado implements Curso {
    @Override
    public boolean verificarAprovacao(Estudante estudante) {
        for (Disciplina disciplina : estudante.getDisciplinas()) {
            if ("D".equals(disciplina.getConceito())) {
                return false; // Se o estudante obtiver o conceito "D" em qualquer disciplina, ele é descredenciado.
            }
        }
        return true; // Se o estudante obtiver apenas conceitos "A", "B" ou "C" em todas as disciplinas, ele é mantido no programa de mestrado.
    }
}
