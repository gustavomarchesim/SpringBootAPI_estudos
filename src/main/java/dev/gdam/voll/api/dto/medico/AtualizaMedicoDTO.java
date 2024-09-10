package dev.gdam.voll.api.dto.medico;

import dev.gdam.voll.api.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizaMedicoDTO(
        @NotNull Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco) {

}
