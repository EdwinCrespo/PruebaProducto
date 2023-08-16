package com.CAMEBOL.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CAMEBOL.producto.entity.CategoriaProducto;
import com.CAMEBOL.producto.repository.CategoriaProductoRepository;



@Service
@Transactional
public class CategoriaProductoService {
	@Autowired
	CategoriaProductoRepository categoriaProductoRepository;
	 public List<CategoriaProducto> getAll(){
	    	return categoriaProductoRepository.findAll();
	    }
	 public List<CategoriaProducto> getAllByEstado(String estado){
	    	return categoriaProductoRepository.findByEstado(estado);
	    }
	 public Optional<CategoriaProducto> getById(int id){
	        return categoriaProductoRepository.findById(id);
	    }
	 public boolean existsById(Integer id){
	        return categoriaProductoRepository.existsById(id);
	    }
	 public void save(CategoriaProducto unidad){
		 categoriaProductoRepository.save(unidad);
	    }
	  public void delete(int id) {
		  categoriaProductoRepository.deleteById(id);
	    }
}
