package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties("transactions")
    private Customer customer;

    @OneToMany (mappedBy = "transaction", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "transaction")
    private List<ChartItem> chartItems;

    @OneToOne(mappedBy = "transaction")
    @JsonIgnoreProperties(value = "transaction")
    private PaymentMethod paymentMethod;
}
