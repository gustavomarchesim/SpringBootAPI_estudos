package dev.gdam.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gdam.voll.api.dto.medico.AtualizaMedicoDTO;
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

  @PostMapping("/cadastro")
  @Transactional
  public void cadastro(@RequestBody @Valid MedicoDTO dados) {
    repository.save(new Medico(dados));
  }

  @GetMapping
  public Page<ListagemMedicoDTO> listar(Pageable paginacao) {
    return repository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDTO::new);
  }

  @PutMapping
  @Transactional
  public void atualizar(@RequestBody @Valid AtualizaMedicoDTO dados) {
    var medico = repository.getReferenceById(dados.id());
    medico.atualizaDados(dados);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void excluir(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.excluir();
  }
}
