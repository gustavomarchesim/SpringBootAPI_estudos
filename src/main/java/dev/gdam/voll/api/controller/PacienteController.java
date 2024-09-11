package dev.gdam.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dev.gdam.voll.api.dto.paciente.AtualizaPacienteDTO;
import dev.gdam.voll.api.dto.paciente.DetalhamentoPacienteDTO;
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
  public ResponseEntity<Page<ListagemPacienteDTO>> listar(
      @PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {
    var page = repository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDTO::new);
    return ResponseEntity.ok(page);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhamentoPacienteDTO> listarDetalhes(@PathVariable Long id) {
    return repository.findById(id)
        .map(medico -> ResponseEntity.ok(new DetalhamentoPacienteDTO(medico)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PostMapping("/cadastro")
  @Transactional
  public ResponseEntity<DetalhamentoPacienteDTO> cadastrar(@RequestBody @Valid PacienteDTO dados,
      UriComponentsBuilder uriBuilder) {
    var paciente = repository.save(new Paciente(dados));

    var uri = uriBuilder.path("pacientes/cadastro/{id}").buildAndExpand(paciente.getId()).toUri();

    return ResponseEntity.created(uri).body(new DetalhamentoPacienteDTO(paciente));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DetalhamentoPacienteDTO> atualizar(@RequestBody @Valid AtualizaPacienteDTO dados) {
    var paciente = repository.getReferenceById(dados.id());
    paciente.atualizarInformacoes(dados);

    return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<PacienteDTO> excluir(@PathVariable Long id) {
    var paciente = repository.getReferenceById(id);
    paciente.inativar();

    return ResponseEntity.noContent().build();
  }
}
