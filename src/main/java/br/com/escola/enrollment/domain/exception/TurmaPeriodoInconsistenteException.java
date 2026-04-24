package br.com.escola.enrollment.domain.exception;

public class TurmaPeriodoInconsistenteException extends RuntimeException {

    public TurmaPeriodoInconsistenteException(Long turmaId, Long periodoLetivoId) {
        super("A turma " + turmaId + " não pertence ao período letivo " + periodoLetivoId);
    }
}
