package br.com.fiap.service;

import br.com.fiap.model.dto.FeedbackReportRequest;
import br.com.fiap.persistence.entity.Aluno;
import br.com.fiap.persistence.entity.Curso;
import br.com.fiap.persistence.entity.Feedback;
import br.com.fiap.persistence.repository.FeedbackRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class FeedbackReportServiceTest {

    @Test
    void findCursoById() {
        // arrange
        FeedbackRepository mockFeedbackRepository = Mockito.mock(FeedbackRepository.class);
        FeedbackReportRequest mockRequest = Mockito.mock(FeedbackReportRequest.class);

        Feedback feedback = Mockito.mock(Feedback.class);
        Curso course = Mockito.mock(Curso.class);
        Aluno student = Mockito.mock(Aluno.class);

        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);

        when(mockFeedbackRepository.getReportsFromPeriod(any())).thenReturn(feedbackList);
        when(feedback.getCourse()).thenReturn(course);
        when(feedback.getStudent()).thenReturn(student);
        when(feedback.getComment()).thenReturn("Test comment");
        when(feedback.getGrade()).thenReturn((short) 8);

        var response = mockFeedbackRepository.getReportsFromPeriod(mockRequest);

        // assert
        assertNotNull(response);
        verify(mockFeedbackRepository, times(1)).getReportsFromPeriod(any());
        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(course, response.get(0).getCourse());
        Assertions.assertEquals(student, response.get(0).getStudent());
        Assertions.assertEquals("Test comment", response.get(0).getComment());
        Assertions.assertEquals((short) 8, response.get(0).getGrade());
    }
}
