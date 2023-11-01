package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.ChartItem;
import br.com.isato.applojalivros.projection.ChartItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartItemRepository extends JpaRepository<ChartItem, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_chart_items.id,
                   tb_chart_items.fk_chart_id AS chartId
            FROM tb_chart_items
            WHERE tb_chart_items.fk_chart_id = :chartId
             """)
    public ChartItemProjection searchByChart(Long chartId);
}
