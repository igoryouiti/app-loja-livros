package br.com.isato.applojalivros.DTO.transactionDTO;

import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.model.TransactionStatus;
import br.com.isato.applojalivros.projection.TransactionProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private LocalDateTime dateTime;
    private BigDecimal totalPrice;
    private TransactionStatus transactionStatus;
    private Customer customer;

    public TransactionDTO(TransactionProjection entity){
        BeanUtils.copyProperties(entity, this);
        customer = new Customer();
        customer.setId(entity.getCustomerId());
    }
}
