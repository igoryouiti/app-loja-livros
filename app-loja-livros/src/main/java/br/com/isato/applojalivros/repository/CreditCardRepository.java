package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.CreditCard;
import br.com.isato.applojalivros.projection.CreditCardProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    @Query(nativeQuery = true, value = """
            SELECT tb_credit_card.id AS id,
                   tb_credit_card.holder_name AS holderName,
                   tb_credit_card.exp_month AS expMonth,
                   tb_credit_card.exp_year AS expYear,
                   tb_credit_card.card_number AS cardNumber,
                   tb_credit_card.cvv AS cvv,
                   tb_credit_card.observation AS observation,
                   tb_credit_card.credit_card_brand AS creditCardBrand,
                   tb_credit_card.fk_customer_id AS customerId
            FROM tb_credit_card
            WHERE tb_credit_card.fk_customer_id = :customerId
             """)
    public List<CreditCardProjection> searchAllByCustomer(Long customerId);
}
