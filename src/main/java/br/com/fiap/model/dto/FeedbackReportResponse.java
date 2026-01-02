package br.com.fiap.model.dto;

public record FeedbackReportResponse(
        String nomeAluno,
        String nomeCurso,
        String comentario,
        Short nota
) {
}
