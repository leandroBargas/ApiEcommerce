package com.apiecommerce.domain.repository.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.apiecommerce.domain.model.Manutencao;
import com.apiecommerce.domain.repository.ManutencaoRepository;

@Component
public class ManutencaoRepositoryImpl implements ManutencaoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Manutencao> listar() {
		return manager.createQuery("from Manutencao", Manutencao.class).getResultList();
	}

	@Override
	public Manutencao buscar(Long id) {
		return manager.find(Manutencao.class, id);
	}

	@Transactional
	@Override
	public Manutencao salvar(Manutencao manutencao) {
		return manager.merge(manutencao);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Manutencao manutencao = buscar(id);

		if (manutencao == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(manutencao);
	}

}