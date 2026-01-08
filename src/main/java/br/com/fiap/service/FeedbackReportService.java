package br.com.fiap.service;

import br.com.fiap.model.dto.FeedbackReportItem;
import br.com.fiap.model.dto.FeedbackReportRequest;
import br.com.fiap.model.dto.FeedbackReportResponse;
import br.com.fiap.persistence.entity.Feedback;
import br.com.fiap.persistence.repository.FeedbackRepository;
import br.com.fiap.presenter.FeedbackPresenter;
import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ApplicationScoped
@Slf4j
@Unremovable
public class FeedbackReportService {

    @Inject
    FeedbackRepository feedbackRepository;

    @Transactional
    public List<FeedbackReportItem> getReportsFromPeriod(FeedbackReportRequest request) {
        log.info("Gerando relatório de feedbacks do período: {} a {}", request.dataInicio(), request.dataFim());

        List<Feedback> feedbacks = feedbackRepository.getReportsFromPeriod(request);

        log.info("Total de feedbacks encontrados: {}", feedbacks.size());

        return feedbacks.stream()
                .map(FeedbackPresenter::toItem)
                .toList();
    }

}
