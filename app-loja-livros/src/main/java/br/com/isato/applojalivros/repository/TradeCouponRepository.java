package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.TradeCoupon;
import br.com.isato.applojalivros.projection.TradeCouponProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeCouponRepository extends JpaRepository<TradeCoupon, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_trade_coupons.id,
                   tb_trade_coupons.active,
                   tb_trade_coupons.created_date as createdDate,
                   tb_trade_coupons.description,
                   tb_trade_coupons.value,
                   tb_trade_coupons.fk_customer_id AS customerId
            FROM tb_trade_coupons
            WHERE tb_trade_coupons.fk_customer_id = :customerId
             """)
    public List<TradeCouponProjection> searchAllByCustomer(Long customerId);


}
