package br.com.fiap.resource;

import br.com.fiap.model.dto.FeedbackReportRequest;
import br.com.fiap.model.dto.FeedbackReportResponse;
import br.com.fiap.service.FeedbackReportService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.quarkus.arc.Arc;

public class FeedbackReportResource implements RequestHandler<FeedbackReportRequest, FeedbackReportResponse> {

    @Override
    public FeedbackReportResponse handleRequest(FeedbackReportRequest request, Context context) {
        context.getLogger().log("Processing feedback report request.");

        var inst = Arc.container().instance(FeedbackReportService.class);
        if (!inst.isAvailable()) {
            throw new IllegalStateException("FeedbackReportService CDI bean not available.");
        }

        var list = inst.get().getReportsFromPeriod(request);
        return new FeedbackReportResponse(list);
    }

}
