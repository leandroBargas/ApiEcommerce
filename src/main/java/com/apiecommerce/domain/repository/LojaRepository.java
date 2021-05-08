package com.apiecommerce.domain.repository;

import java.util.List;

import com.apiecommerce.domain.model.Loja;

public interface LojaRepository  {
	
	List<Loja> listar();
	
	Loja buscar(Long id);
	
	Loja salvar(Loja loja);
	
	void remover(Loja loja);



}
