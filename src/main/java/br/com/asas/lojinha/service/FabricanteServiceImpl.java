package br.com.asas.lojinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.asas.lojinha.entity.Fabricante;
import br.com.asas.lojinha.exception.LojinhaNotFoundException;
import br.com.asas.lojinha.repository.FabricanteRepository;

@Service
public class FabricanteServiceImpl implements FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	@Override
	public Fabricante salvar(Fabricante fabricante) {		
		return fabricanteRepository.save(fabricante);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Fabricante> buscarTodos() {
		return fabricanteRepository.findAll();
	}
	
	@Override
	public Fabricante encontrarUm(Long codigo) throws LojinhaNotFoundException {
		Fabricante fabricante = fabricanteRepository.findOne(codigo);
		if(fabricante == null) {
			throw new LojinhaNotFoundException("Não foi possivel encontrar o fábricante através do código: " + codigo);
		}
		return fabricanteRepository.findOne(codigo);
	}
	
	@Transactional(rollbackFor = {LojinhaNotFoundException.class}	)
	@Override
	public Fabricante atualizar(Fabricante fabricante) {
		return fabricanteRepository.saveAndFlush(fabricante);
	}
	
	@Transactional(rollbackFor = {LojinhaNotFoundException.class})
	@Override
	public Fabricante deletar(Fabricante fabricante) {
		fabricanteRepository.delete(fabricante);
		return fabricante;
	}
}
