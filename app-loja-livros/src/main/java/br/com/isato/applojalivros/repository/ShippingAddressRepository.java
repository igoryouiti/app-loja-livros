package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.ShippingAddress;
import br.com.isato.applojalivros.projection.ShippingAddressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_shipping_address.id, tb_shipping_address.cep, tb_shipping_address.city,
            tb_shipping_address.country, tb_shipping_address.neighborhood,tb_shipping_address.number, 
            tb_shipping_address.observation, tb_shipping_address.public_place AS publicPlace, 
            tb_shipping_address.state, tb_shipping_address.type_public_place AS typePublicPlace, 
            tb_shipping_address.type_residence AS typeResidence, 
            tb_shipping_address.fk_customer_id AS customerId
            FROM tb_shipping_address
            WHERE tb_shipping_address.fk_customer_id = :customerId
             """)
    public List<ShippingAddressProjection> searchAllByCustomer(Long customerId);
}
