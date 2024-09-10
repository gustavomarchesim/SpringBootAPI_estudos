package dev.gdam.voll.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.gdam.voll.api.entity.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

  Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
