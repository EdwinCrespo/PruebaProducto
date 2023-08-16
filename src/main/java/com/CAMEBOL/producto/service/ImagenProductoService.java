package com.CAMEBOL.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CAMEBOL.producto.entity.ImagenProducto;
import com.CAMEBOL.producto.entity.Producto;
import com.CAMEBOL.producto.repository.ImagenProductoRepository;


@Service
@Transactional
public class ImagenProductoService {
	@Autowired
	ImagenProductoRepository imagenProductoRepository;
	
	 public List<ImagenProducto> getAll(){
	    	
	    	return imagenProductoRepository.findAll();
	    }
	 public List<ImagenProducto> getByIdProducto(Producto producto){
	    	
	    	return imagenProductoRepository.findByProducto(producto);
	    }
	 public Optional<ImagenProducto> getById(int id){
	        return imagenProductoRepository.findById(id);
	    }
	 public boolean existsById(Integer id){
	        return imagenProductoRepository.existsById(id);
	    }
	 public void save(ImagenProducto imagenProducto){
		 imagenProductoRepository.save(imagenProducto);
	    }
	  public void delete(int id) {
		  imagenProductoRepository.deleteById(id);
	    }
}
