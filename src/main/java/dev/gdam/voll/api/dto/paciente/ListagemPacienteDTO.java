package dev.gdam.voll.api.dto.paciente;

import dev.gdam.voll.api.entity.paciente.Paciente;

public record ListagemPacienteDTO(Long id, String nome, String email, String cpf) {
  public ListagemPacienteDTO(Paciente paciente) {
    this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
  }
}
