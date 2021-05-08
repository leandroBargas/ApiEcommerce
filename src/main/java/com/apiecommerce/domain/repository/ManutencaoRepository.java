package com.apiecommerce.domain.repository;

import java.util.List;

import com.apiecommerce.domain.model.Manutencao;

public interface ManutencaoRepository {
	
	List<Manutencao>listar();
	
	Manutencao salvar(Manutencao manutencao);
	
	void remover(Long id);

	Manutencao buscar(Long id);
	
}
