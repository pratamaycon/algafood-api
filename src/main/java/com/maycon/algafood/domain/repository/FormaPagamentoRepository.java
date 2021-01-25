package com.maycon.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maycon.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	
}
