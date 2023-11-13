package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.Transaction;
import br.com.isato.applojalivros.projection.TransactionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_transactions.id,
                tb_transactions.date_time AS dateTime,
                tb_transactions.total_price AS totalPrice,
                tb_transactions.transaction_status AS transactionStatus,
                tb_transactions.fk_customer_id AS customerId
            FROM tb_transactions
            WHERE tb_transactions.fk_customer_id = :customerId
             """)
    public List<TransactionProjection> searchAllByCustomer(Long customerId);


}
