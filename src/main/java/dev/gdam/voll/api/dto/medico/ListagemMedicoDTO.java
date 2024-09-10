package dev.gdam.voll.api.dto.medico;

import dev.gdam.voll.api.entity.medico.EspecialidadeEnum;
import dev.gdam.voll.api.entity.medico.Medico;

public record ListagemMedicoDTO(Long id, String nome, String email, String crm, EspecialidadeEnum especialidade) {

  public ListagemMedicoDTO(Medico medico) {
    this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
  }
}
