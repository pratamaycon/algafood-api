package com.maycon.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.maycon.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.maycon.algafood.domain.exception.EstadoInexistenteNaoEncontrada;
import com.maycon.algafood.domain.model.Cidade;
import com.maycon.algafood.domain.model.Estado;
import com.maycon.algafood.domain.repository.CidadeRepository;
import com.maycon.algafood.domain.repository.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade porId(Long codigo) {
		 Cidade cidade = cidadeRepository.buscar(codigo);
		 
		 if (cidade == null) {
			 throw new EntidadeNaoEncontradaException(
						String.format("Não existe um cadastro de cidade com o código %d", codigo));
		 }
		 return cidade;
	}
	
	public Cidade salvar(Cidade cidade) {
		Long cidadeId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(cidadeId);
		
		if (estado == null) {
			throw new EstadoInexistenteNaoEncontrada(
					String.format("Não existe um cadastro de estado com o código %d", cidadeId));
		}
		
		cidade.setEstado(estado);
		
		return cidadeRepository.salvar(cidade);
	}
	
	public void excluir(Long id) {
		try {
			cidadeRepository.remover(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cozinha com o código %d", id));
		}
	}
	
	
	
}
