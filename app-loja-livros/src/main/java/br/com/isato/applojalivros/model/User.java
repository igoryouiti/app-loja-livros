package br.com.isato.applojalivros.model;

import br.com.isato.applojalivros.DTO.CreateUserDTO;
import br.com.isato.applojalivros.DTO.UpdateUserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, message = "A senha deve ter no minimo 8 caracteres")
    private String password;

    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "id")
    private Customer customer;


    public User(CreateUserDTO entity){
        BeanUtils.copyProperties(entity, this);
    }

    public User(UpdateUserDTO entity){
        BeanUtils.copyProperties(entity, this);
    }

}
