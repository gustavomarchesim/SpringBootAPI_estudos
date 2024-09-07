package dev.gdam.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gdam.voll.api.entity.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
