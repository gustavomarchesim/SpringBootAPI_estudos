package dev.gdam.voll.api.entity.paciente;

import dev.gdam.voll.api.dto.paciente.AtualizaPacienteDTO;
import dev.gdam.voll.api.dto.paciente.PacienteDTO;
import dev.gdam.voll.api.entity.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @Column(unique = true)
  private String email;

  private String telefone;

  @Column(unique = true)
  private String cpf;

  @Embedded
  private Endereco endereco;

  private Boolean ativo;

  public Paciente(PacienteDTO dados) {
    this.ativo = true;
    this.nome = dados.nome();
    this.email = dados.email();
    this.cpf = dados.cpf();
    this.telefone = dados.telefone();
    this.endereco = new Endereco(dados.endereco());
  }

  public void atualizarInformacoes(AtualizaPacienteDTO dados) {
    if (dados.nome() != null)
      this.nome = dados.nome();

    if (dados.telefone() != null)
      this.telefone = dados.telefone();

    if (dados.endereco() != null)
      endereco.atualizarEndereco(dados.endereco());
  }

  public void inativar() {
    this.ativo = false;
  }
}
