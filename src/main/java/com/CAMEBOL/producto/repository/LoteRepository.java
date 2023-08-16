package com.CAMEBOL.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CAMEBOL.producto.entity.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {
	List<Lote> findByIdEmpresaAndEstado(int idEmpresa, String estado);
	List<Lote> findByEstado(String estado);
}
