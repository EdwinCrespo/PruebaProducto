package com.CAMEBOL.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CAMEBOL.producto.entity.Lote;
import com.CAMEBOL.producto.repository.LoteRepository;

@Service
@Transactional
public class LoteService {
	@Autowired
	LoteRepository loteRepository;
	 public List<Lote> getAll(){
	    	return loteRepository.findAll();
	    }
	 public List<Lote> getAllByEstado(String estado){
	    	return loteRepository.findByEstado(estado );
	    }
	 public Optional<Lote> getByIdLote(int id){
	        return loteRepository.findById(id);
	    }
	 public List<Lote> getByIdEmpresaAndEstado(int idEmpresa,String estado){
	        return loteRepository.findByIdEmpresaAndEstado(idEmpresa,estado);
	    }
	 public boolean existsByIdLote(Integer id){
	        return loteRepository.existsById(id);
	    }
	 public void save(Lote unidad){
		 loteRepository.save(unidad);
	    }
	  public void delete(int id) {
		  loteRepository.deleteById(id);
	    }
}
