package com.CAMEBOL.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CAMEBOL.producto.entity.Unidad;
import com.CAMEBOL.producto.repository.UnidadRepository;


@Service
@Transactional
public class UnidadService {
	@Autowired
	UnidadRepository unidadRepository;
	 public List<Unidad> getAll(){
	    	return unidadRepository.findAll();
	    }
	 public List<Unidad> getAllByEstado(String estado){
	    	return unidadRepository.findByEstado(estado );
	    }
	 public Optional<Unidad> getById(int id){
	        return unidadRepository.findById(id);
	    }
	 public boolean existsById(Integer id){
	        return unidadRepository.existsById(id);
	    }
	 public void save(Unidad unidad){
		 unidadRepository.save(unidad);
	    }
	  public void delete(int id) {
		  unidadRepository.deleteById(id);
	    }

}
