package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.PromoCouponPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCouponPaymentRepository extends JpaRepository<PromoCouponPayment, Long> {
}
