package dev.gdam.voll.api.dto;

import dev.gdam.voll.api.entity.medico.EspecialidadeEnum;
import dev.gdam.voll.api.entity.medico.Medico;

public record ListagemMedicoDTO(String nome, String email, String crm, EspecialidadeEnum especialidade) {

  public ListagemMedicoDTO(Medico medico) {
    this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
  }
}
