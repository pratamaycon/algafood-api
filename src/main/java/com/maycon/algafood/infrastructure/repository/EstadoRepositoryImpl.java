package com.maycon.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.maycon.algafood.domain.model.Estado;
import com.maycon.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> listar() {
		TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
		return query.getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Estado estado = buscar(id);

		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(estado);
	}

}
