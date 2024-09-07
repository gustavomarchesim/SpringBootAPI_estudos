package dev.gdam.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gdam.voll.api.dto.MedicoDTO;
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
}
