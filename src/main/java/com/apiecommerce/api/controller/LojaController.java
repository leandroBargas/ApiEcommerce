package com.apiecommerce.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiecommerce.domain.exception.EntidadeNaoEncontradaExcepition;
import com.apiecommerce.domain.model.Loja;
import com.apiecommerce.domain.repository.LojaRepository;
import com.apiecommerce.domain.service.CadastroLojaService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping(value = "/lojas")
public class LojaController {

	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private CadastroLojaService cadastroloja;
	
	@GetMapping
	public List<Loja> listar() {
		return lojaRepository.listar();
	}
	
	@GetMapping("/{lojaId}")
	public ResponseEntity<Loja> buscar(@PathVariable Long lojaId) {
		Loja loja = lojaRepository.buscar(lojaId);
		
		if (loja != null) {
			return ResponseEntity.ok(loja);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Loja loja) {
		try {
			loja = cadastroloja.salvar(loja);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(loja);
		} catch (EntidadeNaoEncontradaExcepition e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{lojaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long lojaId,
			@RequestBody Loja loja) {
		try {
			Loja lojaAtual = lojaRepository.buscar(lojaId);
			
			if (lojaAtual != null) {
				BeanUtils.copyProperties(loja, lojaAtual, "id");
				
				lojaAtual = cadastroloja.salvar(lojaAtual);
				return ResponseEntity.ok(lojaAtual);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeNaoEncontradaExcepition e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PatchMapping("/{lojaId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long lojaId,
			@RequestBody Map<String, Object> campos) {
		Loja lojaAtual =lojaRepository.buscar(lojaId);
		
		if (lojaAtual == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, lojaAtual);
		
		return atualizar(lojaId, lojaAtual);
	}
	
	

	private void merge(Map<String, Object> dadosOrigem, Loja lojaDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Loja lojaOrigem = objectMapper.convertValue(dadosOrigem, Loja.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Loja.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, lojaOrigem);
			
//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
			
			ReflectionUtils.setField(field, lojaDestino, novoValor);
		});
	}
	
}