package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.ReasonActivationChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonActivationChangeRepository extends JpaRepository<ReasonActivationChange, Long> {
}
