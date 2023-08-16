package com.CAMEBOL.producto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CAMEBOL.producto.entity.Caracteristica;
import com.CAMEBOL.producto.entity.Mensaje;
import com.CAMEBOL.producto.service.CaracteristicaService;

@RestController
@RequestMapping("/caracteristica")
@CrossOrigin(origins = "*")
public class CaracateristicaController {
	@Autowired
	private CaracteristicaService caracteristicaService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Caracteristica>> getAll(){
		List<Caracteristica> list = caracteristicaService.getAll();
		return  new ResponseEntity<List<Caracteristica>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaPorEstado/{estado}")
	public ResponseEntity<List<Caracteristica>> getAllByEstado(@PathVariable("estado")String estado){
		List<Caracteristica> list = caracteristicaService.getAllByEstado(estado);
		return  new ResponseEntity<List<Caracteristica>>(list,HttpStatus.OK);
	}
	@GetMapping("/obtenerPorIdCaracteristica/{idCaracteristica}")
	public ResponseEntity<Caracteristica> getById(@PathVariable("idCaracteristica") int idCaracteristica){
		if(!caracteristicaService.existsById(idCaracteristica)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		Caracteristica caracteristica=caracteristicaService.getById(idCaracteristica).get();
		return new ResponseEntity(caracteristica,HttpStatus.OK);
	}
	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody Caracteristica caracteristica, BindingResult bindingResult){
	
		caracteristicaService.save(caracteristica);
        
		return new ResponseEntity(new Mensaje("Caracteristica creada"),HttpStatus.CREATED);
    }
	@PutMapping("/actualizar/{idCaracteristica}")
	public ResponseEntity<?> actualizar(@PathVariable("idCaracteristica") int idCaracteristica, @RequestBody Caracteristica caracteristica){

			
		Caracteristica carac=caracteristicaService.getById(idCaracteristica).get();
		carac.setNombre(caracteristica.getNombre());
		carac.setDescripcion(caracteristica.getDescripcion());
		carac.setEstado(caracteristica.getEstado());
		carac.setFechaRegistro(caracteristica.getFechaRegistro());
		carac.setCaracteristicaProductos(caracteristica.getCaracteristicaProductos());
		
		caracteristicaService.save(carac);
		return new ResponseEntity(new Mensaje("Caracteristica Actualizada"),HttpStatus.OK);
	
	}	
	@DeleteMapping("/elimnar/{idCaracteristica}")
	public ResponseEntity<?> delete(@PathVariable("idCaracteristica") int idCaracteristica){
		caracteristicaService.delete(idCaracteristica);
		return new ResponseEntity(new Mensaje("Caracteristica Eliminada"),HttpStatus.OK);
	}
}
