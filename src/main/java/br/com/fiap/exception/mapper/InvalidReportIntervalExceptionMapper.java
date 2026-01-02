package br.com.fiap.exception.mapper;

import br.com.fiap.exception.InvalidReportIntervalException;
import br.com.fiap.exception.utils.ApiErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import software.amazon.awssdk.http.HttpStatusCode;

import java.time.LocalDateTime;

public class InvalidReportIntervalExceptionMapper implements ExceptionMapper<InvalidReportIntervalException> {

    @Override
    public Response toResponse(InvalidReportIntervalException exception) {
        // Build a custom error response object
        ApiErrorMessage errorResponse = new ApiErrorMessage(
                LocalDateTime.now(),
                HttpStatusCode.BAD_REQUEST,
                exception.getMessage(),
                exception.getCause().toString()
        );

        // Return a Response with the desired HTTP status and entity
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}
