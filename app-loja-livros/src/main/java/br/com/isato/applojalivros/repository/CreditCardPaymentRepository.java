package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.CreditCardPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment, Long> {
}
