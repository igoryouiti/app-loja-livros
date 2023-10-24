package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.TradeCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeCouponRepository extends JpaRepository<TradeCoupon, Long> {
}
