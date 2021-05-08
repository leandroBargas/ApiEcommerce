package com.apiecommerce.domain.repository.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.apiecommerce.domain.model.Loja;
import com.apiecommerce.domain.repository.LojaRepository;

@Component
public class LojaRepositoryImpl implements LojaRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Loja> listar(){
		return manager.createQuery("from Loja",Loja.class).getResultList();
		
	}
	
	@Override
	public Loja buscar(Long id) {
		return manager.find(Loja.class, id);
	}
	
	@Transactional
	@Override
	public Loja salvar(Loja loja) {
		return manager.merge(loja);
	}

	@Transactional
	@Override
	public void remover(Loja loja) {
		loja = buscar(loja.getId());
		manager.remove(loja);
	}

}
