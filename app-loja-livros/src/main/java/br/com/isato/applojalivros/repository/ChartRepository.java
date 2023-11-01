package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.Chart;
import br.com.isato.applojalivros.projection.ChartProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_charts.id,
                   tb_charts.fk_customer_id AS customerId
            FROM tb_charts 
            WHERE tb_charts.fk_customer_id = :customerId
             """)
    public ChartProjection searchByCustomer(Long customerId);
}
