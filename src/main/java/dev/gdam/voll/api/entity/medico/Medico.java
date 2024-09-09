package dev.gdam.voll.api.entity.medico;

import dev.gdam.voll.api.dto.MedicoDTO;
import dev.gdam.voll.api.entity.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @Column(unique = true)
  private String email;

  private String telefone;

  @Column(unique = true)
  private String crm;

  @Enumerated(EnumType.STRING)
  private EspecialidadeEnum especialidade;

  @Embedded
  private Endereco endereco;

  public Medico(MedicoDTO dados) {
    this.nome = dados.nome();
    this.email = dados.email();
    this.crm = dados.crm();
    this.telefone = dados.telefone();
    this.especialidade = dados.especialidade(); // Sem necessidade de conversão adicional
    this.endereco = new Endereco(dados.endereco());
  }

}
