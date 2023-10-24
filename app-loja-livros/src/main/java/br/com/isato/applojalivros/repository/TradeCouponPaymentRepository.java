package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.TradeCouponPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeCouponPaymentRepository extends JpaRepository<TradeCouponPayment, Long> {
}
