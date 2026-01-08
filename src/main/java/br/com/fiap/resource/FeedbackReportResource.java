package br.com.fiap.resource;

import br.com.fiap.model.dto.FeedbackReportRequest;
import br.com.fiap.model.dto.FeedbackReportResponse;
import br.com.fiap.service.FeedbackReportService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.quarkus.arc.Arc;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

public class FeedbackReportResource implements RequestHandler<FeedbackReportRequest, List<FeedbackReportResponse>> {

    @Override
    public List<FeedbackReportResponse> handleRequest(FeedbackReportRequest request, Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log("Processing feedback reports submission.");

        FeedbackReportService feedbackReportService = Arc.container()
                .instance(FeedbackReportService.class)
                .get();

        return feedbackReportService.getReportsFromPeriod(request);
    }
}
