package com.maycon.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.maycon.algafood.domain.exception.CozinhaInexistenteNaoEncontrada;
import com.maycon.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.maycon.algafood.domain.model.Cozinha;
import com.maycon.algafood.domain.model.Restaurante;
import com.maycon.algafood.domain.repository.CozinhaRepository;
import com.maycon.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante porId(Long codigo) {
		 Restaurante restaurante = restauranteRepository.buscar(codigo);
		 
		 if (restaurante == null) {
			 throw new EntidadeNaoEncontradaException(
						String.format("Não existe um cadastro de restaurante com o código %d", codigo));
		 }
		 return restaurante;
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if (cozinha == null) {
			throw new CozinhaInexistenteNaoEncontrada(
					String.format("Não existe um cadastro de cozinha com o código %d", cozinhaId));
		}
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}
	
	public void excluir(Long id) {
		try {
			restauranteRepository.remover(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cozinha com o código %d", id));
		}
	}
	
}
