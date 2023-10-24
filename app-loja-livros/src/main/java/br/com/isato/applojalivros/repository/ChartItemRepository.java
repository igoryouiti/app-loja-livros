package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.ChartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartItemRepository extends JpaRepository<ChartItem, Long> {
}
