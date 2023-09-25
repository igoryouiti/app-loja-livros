package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.Dimension;
import br.com.isato.applojalivros.projection.DimensionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, Long> {

    @Query(nativeQuery = true, value = """
            SELECT d.id AS id,
                   d.depth,
                   d.height,
                   d.weight,
                   d.width,
                   d.fk_book_id AS bookId
            FROM tb_dimensions d
            WHERE d.fk_book_id = :bookId
             """)
    public DimensionProjection searchByBook(Long bookId);
}
