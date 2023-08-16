package com.CAMEBOL.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CAMEBOL.producto.entity.DetalleLote;
import com.CAMEBOL.producto.entity.Lote;


@Repository
public interface DetalleLoteRepository extends JpaRepository<DetalleLote, Integer> {
	List<DetalleLote> findByLote (Lote lote);
}
