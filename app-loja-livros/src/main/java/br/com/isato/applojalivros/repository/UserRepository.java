package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
            SELECT u.id,
                   u.email,
                   u.password,
                   u.active,
                   c.id AS customerId
            FROM tb_users u
            JOIN tb_customers c ON u.id = c.fk_user_id
            WHERE c.id = :customerId
             """)
    public UserProjection searchByCustomer(@Param("customerId") Long customerId);

    public Optional<User> findByEmail(String userEmail);
}
