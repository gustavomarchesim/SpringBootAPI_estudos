package dev.gdam.voll.api.dto.paciente;

import dev.gdam.voll.api.dto.EnderecoDTO;
import jakarta.validation.Valid;

public record AtualizaPacienteDTO(
                Long id,
                String nome,
                String telefone,
                @Valid EnderecoDTO endereco) {
}
