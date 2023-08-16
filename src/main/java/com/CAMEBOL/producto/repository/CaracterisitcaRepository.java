package com.CAMEBOL.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CAMEBOL.producto.entity.Caracteristica;

@Repository
public interface CaracterisitcaRepository extends JpaRepository<Caracteristica, Integer> {
	List<Caracteristica> findByEstado(String estado);
}
