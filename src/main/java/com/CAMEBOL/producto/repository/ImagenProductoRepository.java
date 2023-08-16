package com.CAMEBOL.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CAMEBOL.producto.entity.ImagenProducto;
import com.CAMEBOL.producto.entity.Producto;


@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Integer> {
	List<ImagenProducto> findByProducto(Producto producto);
}
