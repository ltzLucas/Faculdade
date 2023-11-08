package modelo;

public class CursoBacharelado implements Curso {
    @Override
    public boolean verificarAprovacao(Estudante estudante) {
        for (Disciplina disciplina : estudante.getDisciplinas()) {
            if (disciplina.getMediaAprovacao() < 6.0) {
                return false; // Se qualquer disciplina tiver nota menor que 6.0, o estudante é reprovado.
            }
        }
        return true; // Se todas as disciplinas tiverem nota igual ou superior a 6.0, o estudante é aprovado.
    }
}
