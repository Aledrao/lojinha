package br.com.asas.lojinha.service;

import java.util.List;

import br.com.asas.lojinha.entity.Fabricante;
import br.com.asas.lojinha.exception.LojinhaNotFoundException;

public interface FabricanteService {

	Fabricante salvar(Fabricante fabricante);	
	List<Fabricante> buscarTodos();	
	Fabricante encontrarUm(Long codigo) throws LojinhaNotFoundException;
	Fabricante atualizar(Fabricante fabricante);
	Fabricante deletar(Fabricante fabricante);
}
