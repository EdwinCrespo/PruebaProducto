package com.CAMEBOL.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CAMEBOL.producto.entity.Unidad;


@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Integer> {
	List<Unidad> findByEstado(String estado);
}
