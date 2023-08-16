package com.CAMEBOL.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CAMEBOL.producto.entity.CaracteristicaProducto;
import com.CAMEBOL.producto.entity.Producto;

@Repository
public interface CaracteristicaProductoRepository extends JpaRepository<CaracteristicaProducto, Integer>{
	
	List<CaracteristicaProducto> findByProducto(Producto producto);
}
