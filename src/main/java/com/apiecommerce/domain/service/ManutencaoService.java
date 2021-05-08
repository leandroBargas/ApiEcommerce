package com.apiecommerce.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apiecommerce.domain.exception.EntidadeEmUsoException;
import com.apiecommerce.domain.exception.EntidadeNaoEncontradaExcepition;
import com.apiecommerce.domain.model.Manutencao;
import com.apiecommerce.domain.repository.ManutencaoRepository;

@Service
public class ManutencaoService {

	@Autowired
	private ManutencaoRepository manutencaoRepository;

	public Manutencao salvar(Manutencao manutencao) {
		return manutencaoRepository.salvar(manutencao);
	}
	
	public void excluir(Long manutencaoId) {
		
		try {
			manutencaoRepository.remover(manutencaoId);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepition(String.format("Não existe um cadastro de manutenção com o codigo %d", manutencaoId));
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("manutencão de código %d não pode ser removida, pois está em uso",manutencaoId));
		}
		
	}

}
