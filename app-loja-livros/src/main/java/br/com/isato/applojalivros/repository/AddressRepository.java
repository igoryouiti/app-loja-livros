package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.Address;
import br.com.isato.applojalivros.projection.AddressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_address.id, tb_address.cep, tb_address.city, tb_address.country, tb_address.neighborhood,
            tb_address.number, tb_address.observation, tb_address.public_place AS publicPlace, tb_address.state,
            tb_address.type_public_place AS typePublicPlace, tb_address.type_residence AS typeResidence,
            tb_address.fk_customer_id AS customerId
            FROM tb_address
            WHERE tb_address.fk_customer_id = :customerId
             """)
    public AddressProjection searchByCustomer(Long customerId);

}
