package dev.gdam.voll.api.dto.paciente;

import dev.gdam.voll.api.entity.Endereco;
import dev.gdam.voll.api.entity.paciente.Paciente;

public record DetalhamentoPacienteDTO(Long id, String nome, String email, String cpf, String telefone,
    Endereco endereco) {
  public DetalhamentoPacienteDTO(Paciente paciente) {
    this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(),
        paciente.getEndereco());
  }
}
