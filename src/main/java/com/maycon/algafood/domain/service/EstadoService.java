package com.maycon.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.maycon.algafood.domain.exception.EntidadeEmUsoException;
import com.maycon.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.maycon.algafood.domain.model.Estado;
import com.maycon.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public void excluir(Long id) {
		try {
			estadoRepository.remover(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de estado com o código %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removida, pois está em uso", id));
		}
			
	}
	
}
