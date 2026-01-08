package br.com.fiap.presenter;

import br.com.fiap.model.dto.FeedbackReportItem;
import br.com.fiap.model.dto.FeedbackReportResponse;
import br.com.fiap.persistence.entity.Feedback;

public final class FeedbackPresenter {
    private FeedbackPresenter() {}

    public static FeedbackReportItem toItem(Feedback feedback) {
        return new FeedbackReportItem(
                feedback.getStudent().getName(),
                feedback.getCourse().getName(),
                feedback.getComment(),
                feedback.getGrade()

        );
    }
}


