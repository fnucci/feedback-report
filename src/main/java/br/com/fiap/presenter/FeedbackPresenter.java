package br.com.fiap.presenter;

import br.com.fiap.model.dto.FeedbackReportResponse;
import br.com.fiap.persistence.entity.Feedback;

public class FeedbackPresenter {


    public static FeedbackReportResponse toResponse(Feedback feedback){
        return new FeedbackReportResponse(
                feedback.getStudent().getName(),
                feedback.getCourse().getName(),
                feedback.getComment(),
                feedback.getGrade()
        );
    }
}

