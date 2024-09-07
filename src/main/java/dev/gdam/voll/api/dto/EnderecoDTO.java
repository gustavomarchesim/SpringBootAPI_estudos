package dev.gdam.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
    @NotBlank String logradouro,
    @NotBlank String bairro,
    @NotBlank String cidade,
    @NotBlank String UF,
    @NotBlank @Pattern(regexp = "\\d{8}") String cep,
    String numero,
    String complemento) {
}
