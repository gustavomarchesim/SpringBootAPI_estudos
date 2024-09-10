package dev.gdam.voll.api.dto.paciente;

import dev.gdam.voll.api.dto.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteDTO(
                @NotBlank String nome,
                @NotBlank @Email String email,
                @NotBlank String cpf,
                @NotNull String telefone,
                @NotNull @Valid EnderecoDTO endereco) {

}
