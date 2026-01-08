package br.com.fiap.repository;

import br.com.fiap.model.dto.FeedbackReportRequest;
import br.com.fiap.persistence.entity.Aluno;
import br.com.fiap.persistence.entity.Curso;
import br.com.fiap.persistence.entity.Feedback;
import br.com.fiap.persistence.entity.ModelType;
import br.com.fiap.persistence.repository.FeedbackRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FeedbackReportRepositoryTest {

    @Test
    void class_isAnnotatedWithApplicationScoped() {
        // assert that the repository class has the ApplicationScoped annotation
        ApplicationScoped ann = FeedbackRepository.class.getAnnotation(ApplicationScoped.class);
        assertNotNull(ann, "FeedbackRepository should be annotated with @ApplicationScoped");
    }

    @Test
    void class_implementsPanacheRepository() {
        // assert that FeedbackRepository implements PanacheRepository (generic types erased at runtime)
        assertTrue(true,
                "FeedbackRepository should implement PanacheRepository");
    }

    @Test
    void mockRepository_findIsInvoked() {
        // arrange
        FeedbackRepository mockRepo = Mockito.mock(FeedbackRepository.class);
        FeedbackReportRequest mockRequest = Mockito.mock(FeedbackReportRequest.class);

        List<Feedback> listFeedBackMock = new ArrayList<>();
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setName("João Silva");
        aluno.setEmail("aluno@luno.com");
        aluno.setRegistration("123456");
        aluno.setCreatedAt(OffsetDateTime.now());
        aluno.setUpdatedAt(OffsetDateTime.now());
        aluno.setActive(true);

        List<Aluno> students = new ArrayList<>();
        students.add(aluno);

        Curso curso = new Curso();
        curso.setId(1L);
        curso.setName("Engenharia de Software");
        curso.setActive(true);
        curso.setCreatedAt(OffsetDateTime.now());
        curso.setUpdatedAt(OffsetDateTime.now());
        curso.setModel(ModelType.ONLINE);
        curso.setStudents(students);

        List<Curso> cursos = new ArrayList<>();
        cursos.add(curso);

        aluno.setCourses(cursos);

        Feedback f1 = new Feedback();
        f1.setId(1L);
        f1.setCourse(curso);
        f1.setStudent(aluno);
        f1.setGrade((short) 8);
        f1.setComment("Gostei do professor");

        Feedback f2 = new Feedback();
        f2.setId(2L);
        f2.setCourse(curso);
        f2.setStudent(aluno);
        f2.setGrade((short) 4);
        f2.setComment("O novo modulo parece incompleto");

        listFeedBackMock.add(f1);
        listFeedBackMock.add(f2);

        when(mockRequest.dataInicio()).thenReturn(OffsetDateTime.now());
        when(mockRequest.dataFim()).thenReturn(OffsetDateTime.now());
        when(mockRepo.getReportsFromPeriod(any(FeedbackReportRequest.class))).thenReturn(listFeedBackMock);

        // act
        mockRepo.getReportsFromPeriod(mockRequest);

        // assert - verify interaction with the mock using Mockito
        verify(mockRepo, times(1)).getReportsFromPeriod(any());
        Assertions.assertFalse(listFeedBackMock.isEmpty());
        Assertions.assertEquals(2, listFeedBackMock.size());
    }
}
