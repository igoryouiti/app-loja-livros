package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public List<Customer> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
