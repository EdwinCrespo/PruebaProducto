package com.CAMEBOL.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CAMEBOL.producto.entity.DetalleLote;
import com.CAMEBOL.producto.entity.Lote;
import com.CAMEBOL.producto.repository.DetalleLoteRepository;





@Service
@Transactional
public class DetalleLoteService {
	@Autowired
	DetalleLoteRepository detalleLoteRepository;

	 public List<DetalleLote> getAll(){
	    	return detalleLoteRepository.findAll();
	    }
	 public List<DetalleLote> getAllByLote(Lote lote){
	    	return detalleLoteRepository.findByLote(lote );
	    }
	 public Optional<DetalleLote> getByIdDetalleLote(int id){
	        return detalleLoteRepository.findById(id);
	    }
	 public boolean existsByIdDetalleLote(Integer id){
	        return detalleLoteRepository.existsById(id);
	    }
	 public void save(DetalleLote detalleLote){
		 detalleLoteRepository.save(detalleLote);
	    }
	  public void delete(int id) {
		  detalleLoteRepository.deleteById(id);
	    }
}
