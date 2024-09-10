package dev.gdam.voll.api.entity;

import dev.gdam.voll.api.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
  private String logradouro;
  private String numero;
  private String bairro;
  private String cidade;
  private String UF;
  private String cep;
  private String complemento;

  public Endereco(EnderecoDTO endereco) {
    this.logradouro = endereco.logradouro();
    this.bairro = endereco.bairro();
    this.cep = endereco.cep();
    this.UF = endereco.UF();
    this.cidade = endereco.cidade();
    this.numero = endereco.numero();
    this.complemento = endereco.complemento();
  }

  public void atualizarEndereco(EnderecoDTO dados) {
    if (dados.logradouro() != null) {
      this.logradouro = dados.logradouro();
    }
    if (dados.bairro() != null) {
      this.bairro = dados.bairro();
    }
    if (dados.cep() != null) {
      this.cep = dados.cep();
    }
    if (dados.UF() != null) {
      this.UF = dados.UF();
    }
    if (dados.cidade() != null) {
      this.cidade = dados.cidade();
    }
    if (dados.numero() != null) {
      this.numero = dados.numero();
    }
    if (dados.complemento() != null) {
      this.complemento = dados.complemento();
    }
  }

}
