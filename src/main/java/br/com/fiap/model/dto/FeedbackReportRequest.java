package br.com.fiap.model.dto;

import br.com.fiap.exception.InvalidReportIntervalException;

import java.time.OffsetDateTime;

public record FeedbackReportRequest(
        OffsetDateTime dataInicio,
        OffsetDateTime dataFim
) {
    public FeedbackReportRequest {

        OffsetDateTime hoje = OffsetDateTime.now();

        if (dataInicio == null) {
            dataInicio = hoje.minusDays(7);
        }

        if (dataFim == null) {
            dataFim = hoje;
        }

        if (dataInicio.isAfter(dataFim)) {
            throw new InvalidReportIntervalException("A data de inicio do relatório deve ser anterior a data fim");
        }
    }
}
