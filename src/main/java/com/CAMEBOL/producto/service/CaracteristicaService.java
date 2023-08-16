package com.CAMEBOL.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CAMEBOL.producto.entity.Caracteristica;
import com.CAMEBOL.producto.repository.CaracterisitcaRepository;


@Service
@Transactional
public class CaracteristicaService {
	@Autowired
	CaracterisitcaRepository caracterisitcaRepository;
	 public List<Caracteristica> getAll(){
	    	return caracterisitcaRepository.findAll();
	    }
	 public List<Caracteristica> getAllByEstado(String estado){
	    	return caracterisitcaRepository.findByEstado(estado);
	    }
	 public Optional<Caracteristica> getById(int id){
	        return caracterisitcaRepository.findById(id);
	    }
	 public boolean existsById(Integer id){
	        return caracterisitcaRepository.existsById(id);
	    }
	 public void save(Caracteristica caracteristica){
		 caracterisitcaRepository.save(caracteristica);
	    }
	  public void delete(int id) {
		  caracterisitcaRepository.deleteById(id);
	    }

}
