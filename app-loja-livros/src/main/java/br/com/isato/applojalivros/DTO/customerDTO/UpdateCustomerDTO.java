package br.com.isato.applojalivros.DTO.customerDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerDTO {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 2, max = 128, message = "O nome deve ter no mínimo 2 e no máximo 128 caracteres.")
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private LocalDate birthday;

    @NotBlank
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres numéricos")
    private String cpf;
}
