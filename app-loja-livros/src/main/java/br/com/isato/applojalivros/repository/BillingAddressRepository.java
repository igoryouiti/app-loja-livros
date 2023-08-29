package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.BillingAddress;
import br.com.isato.applojalivros.projection.BillingAddressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_billing_address.id, tb_billing_address.cep, tb_billing_address.city,
            tb_billing_address.country, tb_billing_address.neighborhood,tb_billing_address.number, 
            tb_billing_address.observation, tb_billing_address.public_place AS publicPlace, 
            tb_billing_address.state, tb_billing_address.type_public_place AS typePublicPlace, 
            tb_billing_address.type_residence AS typeResidence, tb_billing_address.fk_customer_id AS customerId
            FROM tb_billing_address
            WHERE tb_address.id = :customerId
             """)
    public List<BillingAddressProjection> searchAllByCustomer(Long customerId);
}
