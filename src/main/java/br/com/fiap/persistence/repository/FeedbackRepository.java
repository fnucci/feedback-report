package br.com.fiap.persistence.repository;

import br.com.fiap.model.dto.FeedbackReportRequest;
import br.com.fiap.persistence.entity.Feedback;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FeedbackRepository implements PanacheRepository<Feedback> {

    public List<Feedback> getReportsFromPeriod(FeedbackReportRequest request) {
        return find("created_at BETWEEN :inicio AND :fim",
                Parameters.with("inicio", request.dataInicio()).and("fim", request.dataFim())).list();
    }

}
