package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.BookStockTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStockTempRepository extends JpaRepository<BookStockTemp, Long> {
}
