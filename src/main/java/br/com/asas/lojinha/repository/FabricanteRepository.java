package br.com.asas.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.asas.lojinha.entity.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
	
}
