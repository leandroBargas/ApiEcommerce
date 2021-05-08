package com.apiecommerce.domain.repository;

import java.util.List;

import com.apiecommerce.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> Listar();
	
	Permissao buscar (Long id);
	
	Permissao salvar (Permissao permissao);
	
	void remover (Permissao permissap);
	

}
