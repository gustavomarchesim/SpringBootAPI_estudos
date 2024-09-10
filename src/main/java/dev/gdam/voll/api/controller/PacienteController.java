package dev.gdam.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gdam.voll.api.dto.paciente.AtualizaPacienteDTO;
import dev.gdam.voll.api.dto.paciente.ListagemPacienteDTO;
import dev.gdam.voll.api.dto.paciente.PacienteDTO;
import dev.gdam.voll.api.entity.paciente.Paciente;
import dev.gdam.voll.api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

  @Autowired
  private PacienteRepository repository;

  @GetMapping
  @Transactional
  public Page<ListagemPacienteDTO> listar(@PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {
    return repository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDTO::new);
  }

  @PostMapping("/cadastro")
  @Transactional
  public void cadastrar(@RequestBody @Valid PacienteDTO dados) {
    repository.save(new Paciente(dados));
  }

  @PutMapping
  @Transactional
  public void atualizar(@RequestBody @Valid AtualizaPacienteDTO dados) {
    var paciente = repository.getReferenceById(dados.id());
    paciente.atualizarInformacoes(dados);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void excluir(Long id) {
    var paciente = repository.getReferenceById(id);
    paciente.inativar();
  }
}
