package dev.gdam.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dev.gdam.voll.api.dto.medico.AtualizaMedicoDTO;
import dev.gdam.voll.api.dto.medico.DetalhamentoMedicoDTO;
import dev.gdam.voll.api.dto.medico.ListagemMedicoDTO;
import dev.gdam.voll.api.dto.medico.MedicoDTO;
import dev.gdam.voll.api.entity.medico.Medico;
import dev.gdam.voll.api.repository.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

  @Autowired
  private MedicoRepository repository;

  @GetMapping
  public ResponseEntity<Page<ListagemMedicoDTO>> listar(Pageable paginacao) {
    var page = repository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDTO::new);
    return ResponseEntity.ok(page);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhamentoMedicoDTO> listarDetalhes(@PathVariable Long id) {
    return repository.findById(id)
        .map(medico -> ResponseEntity.ok(new DetalhamentoMedicoDTO(medico)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PostMapping("/cadastro")
  @Transactional
  public ResponseEntity<DetalhamentoMedicoDTO> cadastro(@RequestBody @Valid MedicoDTO dados,
      UriComponentsBuilder uriBuilder) {
    var medico = repository.save(new Medico(dados));
    var uri = uriBuilder.path("medicos/cadastro/{id}").buildAndExpand(medico.getId()).toUri();
    return ResponseEntity.created(uri).body(new DetalhamentoMedicoDTO(medico));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DetalhamentoMedicoDTO> atualizar(@RequestBody @Valid AtualizaMedicoDTO dados) {
    var medico = repository.getReferenceById(dados.id());
    medico.atualizaDados(dados);
    return ResponseEntity.ok(new DetalhamentoMedicoDTO(medico));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<MedicoDTO> excluir(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.excluir();
    return ResponseEntity.noContent().build();
  }
}
