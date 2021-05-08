package com.apiecommerce.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiecommerce.domain.exception.EntidadeNaoEncontradaExcepition;
import com.apiecommerce.domain.model.Loja;
import com.apiecommerce.domain.model.Manutencao;
import com.apiecommerce.domain.repository.LojaRepository;
import com.apiecommerce.domain.repository.ManutencaoRepository;

@Service
public class CadastroLojaService {

	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private ManutencaoRepository manutencaoRepository;
	
	public Loja salvar(Loja loja) {
		Long manutencaoId = loja.getManutencao().getId();
		Manutencao manutencao = manutencaoRepository.buscar(manutencaoId);
		
		if (manutencao == null) {
			throw new EntidadeNaoEncontradaExcepition(
				String.format("Não existe cadastro de Manutencao com código %d", manutencaoId));
		}
		
		loja.setManutencao(manutencao);
		
		return lojaRepository.salvar(loja);
	}
	
}