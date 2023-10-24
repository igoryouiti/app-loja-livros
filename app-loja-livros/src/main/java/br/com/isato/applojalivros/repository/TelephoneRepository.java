package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.Telephone;
import br.com.isato.applojalivros.projection.TelephoneProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_phone.id,
                   tb_phone.type,
                   tb_phone.ddd,
                   tb_phone.number,
                   tb_phone.fk_customer_id AS customerId
            FROM tb_telephone tb_phone
            WHERE tb_phone.fk_customer_id = :customerId
             """)
    public TelephoneProjection searchByCustomer(Long customerId);
}
