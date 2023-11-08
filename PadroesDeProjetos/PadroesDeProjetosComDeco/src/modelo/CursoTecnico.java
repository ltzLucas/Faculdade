package modelo;

import java.util.List;

public class CursoTecnico implements Curso {
    @Override
    public boolean verificarAprovacao(Estudante estudante) {
        List<Disciplina> disciplinas = estudante.getDisciplinas();
        double somaNotas = 0;

        for (Disciplina disciplina : disciplinas) {
            somaNotas += disciplina.getMediaAprovacao();
        }

        double mediaGeral = somaNotas / disciplinas.size();

        return mediaGeral >= 7.0;
    }
}

