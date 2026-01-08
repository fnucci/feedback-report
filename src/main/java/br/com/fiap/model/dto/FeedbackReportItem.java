package br.com.fiap.model.dto;

public record FeedbackReportItem(
        String nomeAluno,
        String nomeCurso,
        String comentario,
        Short nota
) {}