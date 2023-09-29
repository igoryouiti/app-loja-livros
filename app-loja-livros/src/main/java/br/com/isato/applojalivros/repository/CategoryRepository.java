package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.Category;
import br.com.isato.applojalivros.projection.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value = """
            SELECT c.id AS id,
                   c.description,
                   bc.book_id AS bookId
            FROM tb_categories c
            JOIN books_categories bc ON c.id = bc.category_id
                    WHERE bc.book_id = :bookId
             """)
    public List<CategoryProjection> searchAllByBook(Long bookId);
}

