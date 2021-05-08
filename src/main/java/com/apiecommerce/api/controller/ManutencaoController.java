package com.apiecommerce.api.controller;

import java.util.List;

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

import com.apiecommerce.domain.exception.EntidadeEmUsoException;
import com.apiecommerce.domain.exception.EntidadeNaoEncontradaExcepition;
import com.apiecommerce.domain.model.Manutencao;
import com.apiecommerce.domain.repository.ManutencaoRepository;
import com.apiecommerce.domain.service.ManutencaoService;

@RestController
@RequestMapping(value = "/manutencoes")
public class ManutencaoController {

	@Autowired
	private ManutencaoRepository manutencaoRepository;

	@Autowired
	private ManutencaoService cadastroManutencao;

	@GetMapping
	public List<Manutencao> listar() {
		return manutencaoRepository.listar();
	}

	@GetMapping("/{manutencaoId}")
	public ResponseEntity<Manutencao> buscar(@PathVariable Long manutencaoId) {
		Manutencao manutencao = manutencaoRepository.buscar(manutencaoId);

		if (manutencao != null) {
			return ResponseEntity.ok(manutencao);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Manutencao adicionar(@RequestBody Manutencao manutencao) {
		return cadastroManutencao.salvar(manutencao);
	}

	@PutMapping("/{manutencaoId}")
	public ResponseEntity<Manutencao> atualizar(@PathVariable Long manutencaoId, @RequestBody Manutencao manutencao) {
		Manutencao manutencaoAtual = manutencaoRepository.buscar(manutencaoId);

		if (manutencaoAtual != null) {
			BeanUtils.copyProperties(manutencao, manutencaoAtual, "id");

			manutencaoAtual = cadastroManutencao.salvar(manutencaoAtual);
			return ResponseEntity.ok(manutencaoAtual);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{manutencaoId}")
	public ResponseEntity<?> remover(@PathVariable Long manutencaoId) {
		try {
			cadastroManutencao.excluir(manutencaoId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaExcepition e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}