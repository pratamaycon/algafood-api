package com.maycon.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maycon.algafood.domain.exception.CozinhaInexistenteNaoEncontrada;
import com.maycon.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.maycon.algafood.domain.model.Restaurante;
import com.maycon.algafood.domain.repository.RestauranteRepository;
import com.maycon.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteService restauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long codigo) {
		try {
			Restaurante restaurante = restauranteService.porId(codigo);
			return ResponseEntity.ok(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = restauranteService.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<?> atualizar(@PathVariable Long codigo, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteService.porId(codigo);
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
			restauranteAtual = restauranteService.salvar(restauranteAtual);
			return ResponseEntity.ok(restauranteAtual);
		} catch (CozinhaInexistenteNaoEncontrada e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{codigo}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long codigo, @RequestBody Map<String, Object> campos) {
		
		Restaurante restauranteAtual = restauranteService.porId(codigo);
		
		merge(campos, restauranteAtual); 
		
		return atualizar(codigo, restauranteAtual);

	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
		
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long codigo) {
		try {
			restauranteService.excluir(codigo);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		}
	}

}
