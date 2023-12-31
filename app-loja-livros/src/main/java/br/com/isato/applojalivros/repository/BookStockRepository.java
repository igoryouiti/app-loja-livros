package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStockRepository extends JpaRepository<BookStock, Long> {
}
