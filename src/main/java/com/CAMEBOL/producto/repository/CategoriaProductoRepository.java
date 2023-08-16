package com.CAMEBOL.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CAMEBOL.producto.entity.CategoriaProducto;



@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
	List<CategoriaProducto> findByEstado(String estado);
}
