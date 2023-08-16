package com.CAMEBOL.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CAMEBOL.producto.entity.CaracteristicaProducto;
import com.CAMEBOL.producto.entity.ImagenProducto;
import com.CAMEBOL.producto.entity.Producto;
import com.CAMEBOL.producto.repository.CaracteristicaProductoRepository;
import com.CAMEBOL.producto.repository.ImagenProductoRepository;
@Service
@Transactional
public class CaracteristicaProductoService {
	@Autowired
	CaracteristicaProductoRepository caracteristicaProductoRepository;
	
	 public List<CaracteristicaProducto> getAll(){
	    	
	    	return caracteristicaProductoRepository.findAll();
	    }
	 public List<CaracteristicaProducto> getByIdProducto(Producto producto){
	    	
	    	return caracteristicaProductoRepository.findByProducto(producto);
	    }
	 public Optional<CaracteristicaProducto> getByIdCaracteristicaProducto(int id){
	        return caracteristicaProductoRepository.findById(id);
	    }
	 public boolean existsByIdCaracteristicaProducto(Integer id){
	        return caracteristicaProductoRepository.existsById(id);
	    }
	 public void save(CaracteristicaProducto caracteristicaProducto){
		 caracteristicaProductoRepository.save(caracteristicaProducto);
	    }
	  public void delete(int id) {
		  caracteristicaProductoRepository.deleteById(id);
	    }
}
