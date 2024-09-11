package dev.gdam.voll.api.dto.medico;

import dev.gdam.voll.api.entity.Endereco;
import dev.gdam.voll.api.entity.medico.EspecialidadeEnum;
import dev.gdam.voll.api.entity.medico.Medico;

public record DetalhamentoMedicoDTO(Long id, String nome, String email, String crm, String telefone,
    EspecialidadeEnum Especialidade,
    Endereco endereco, Boolean ativo) {
  public DetalhamentoMedicoDTO(Medico medico) {
    this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
        medico.getEspecialidade(),
        medico.getEndereco(), medico.getAtivo());
  }
}
