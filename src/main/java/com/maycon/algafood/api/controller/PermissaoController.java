package com.maycon.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maycon.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.maycon.algafood.domain.model.Permissao;
import com.maycon.algafood.domain.repository.PermissaoRepository;
import com.maycon.algafood.domain.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@GetMapping
	public List<Permissao> listar() {
		return permissaoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Permissao> buscarPorId(@PathVariable Long codigo) {
		Optional<Permissao> permissao = permissaoRepository.findById(codigo);

		if (permissao.isPresent()) {
			return ResponseEntity.ok(permissao.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Permissao adicionar(@RequestBody Permissao permissao) {
		return permissaoService.salvar(permissao);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Permissao> atualizar(@PathVariable Long codigo, @RequestBody Permissao permissao) {
		Optional<Permissao> permissaoAtual = permissaoRepository.findById(codigo);

		if (permissaoAtual.isPresent()) {
			BeanUtils.copyProperties(permissao, permissaoAtual.get(), "id");
			Permissao permissaoSalva = permissaoService.salvar(permissaoAtual.get());

			return ResponseEntity.ok(permissaoSalva);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable Long codigo) {
		try {
			permissaoService.excluir(codigo);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		}

	}

}
