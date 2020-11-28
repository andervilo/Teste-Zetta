package com.zetta.teste.arquitetura.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zetta.teste.arquitetura.entity.BaseEntity;

public interface GenericService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

	public abstract Page<?> findAll(Pageable pageable);

	public abstract E findById(Long id);

	public abstract E create(E object);

	public abstract E update(Long id, E object);

	public abstract boolean delete(Long id);

	public JpaRepository<E, Long> getRepository();

}
