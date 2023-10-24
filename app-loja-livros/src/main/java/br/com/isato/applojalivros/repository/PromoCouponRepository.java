package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.PromoCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCouponRepository extends JpaRepository<PromoCoupon, Long> {
}
