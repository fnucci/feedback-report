package br.com.fiap.model.dto;

import br.com.fiap.exception.InvalidReportIntervalException;

import java.time.OffsetDateTime;

public record FeedbackReportRequest(
        OffsetDateTime dataInicio,
        OffsetDateTime dataFim
) {
    public FeedbackReportRequest {

        if (dataInicio == null) {
            dataInicio = OffsetDateTime.now();
        }

        if (dataFim == null) {
            dataFim = dataInicio.minusDays(7);
        }

        if (dataInicio.isAfter(dataFim)) {
            throw new InvalidReportIntervalException("A data de inicio do relatório deve ser anterior a data fim");
        }
    }
}
