package br.com.fiap.resource;

import br.com.fiap.model.dto.FeedbackReportRequest;
import br.com.fiap.model.dto.FeedbackReportResponse;
import br.com.fiap.service.FeedbackReportService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class FeedbackReportResourceTest {

    @Test
    void handleRequestTest() {
        // arrange
        FeedbackReportService mockService = Mockito.mock(FeedbackReportService.class);
        FeedbackReportResource resource = new FeedbackReportResource();
        resource.feedbackReportService = mockService; // package-private field accessible within same package

        FeedbackReportRequest request = Mockito.mock(FeedbackReportRequest.class);
        FeedbackReportResponse expected = Mockito.mock(FeedbackReportResponse.class);
        List<FeedbackReportResponse> expectedList = new ArrayList<>();
        expectedList.add(expected);

        Context mockContext = Mockito.mock(Context.class);
        LambdaLogger mockLogger = Mockito.mock(LambdaLogger.class);
        Mockito.when(mockContext.getLogger()).thenReturn(mockLogger);

        Mockito.when(mockService.getReportsFromPeriod(request)).thenReturn(expectedList);

        // act
        List<FeedbackReportResponse> actual = resource.handleRequest(request, mockContext);

        // assert
        assertSame(expectedList, actual);
        Mockito.verify(mockContext).getLogger();
        Mockito.verify(mockLogger).log("Processing feedback reports submission.");
    }

}
