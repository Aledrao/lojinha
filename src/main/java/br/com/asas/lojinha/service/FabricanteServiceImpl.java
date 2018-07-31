package br.com.asas.lojinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.asas.lojinha.entity.Fabricante;
import br.com.asas.lojinha.repository.FabricanteRepository;

@Service
public class FabricanteServiceImpl implements FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	@Override
	public Fabricante salvar(Fabricante fabricante) {		
		return fabricanteRepository.save(fabricante);
	}
	
	@Override
	public List<Fabricante> buscarTodos() {
		return fabricanteRepository.findAll();
	}
	
	@Override
	public Fabricante encontrarUm(Long codigo) {
		return fabricanteRepository.findOne(codigo);
	}
	
	@Override
	public Fabricante atualizar(Fabricante fabricante) {
		return fabricanteRepository.saveAndFlush(fabricante);
	}
	
	@Override
	public Fabricante deletar(Fabricante fabricante) {
		fabricanteRepository.delete(fabricante);
		return fabricante;
	}
}
