package br.com.fiap.service;

import br.com.fiap.model.dto.FeedbackReportRequest;
import br.com.fiap.model.dto.FeedbackReportResponse;
import br.com.fiap.persistence.entity.Feedback;
import br.com.fiap.persistence.repository.FeedbackRepository;
import br.com.fiap.presenter.FeedbackPresenter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FeedbackReportService {

    @Inject
    FeedbackRepository feedbackRepository;

    @Transactional
    public List<FeedbackReportResponse> getReportsFromPeriod(FeedbackReportRequest request) {
        List<Feedback> feedbacks = feedbackRepository.getReportsFromPeriod(request);
        return feedbacks.stream()
                .map(FeedbackPresenter::toResponse)
                .toList();
    }
}
