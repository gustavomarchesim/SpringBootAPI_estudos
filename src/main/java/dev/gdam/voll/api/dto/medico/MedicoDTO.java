package dev.gdam.voll.api.dto.medico;

import dev.gdam.voll.api.dto.EnderecoDTO;
import dev.gdam.voll.api.entity.medico.EspecialidadeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTO(
    @NotBlank String nome,
    @NotBlank @Email String email,
    @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
    @NotNull String telefone,
    @NotNull EspecialidadeEnum especialidade,
    @NotNull @Valid EnderecoDTO endereco) {
}
