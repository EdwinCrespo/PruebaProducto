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

import com.CAMEBOL.producto.entity.Mensaje;
import com.CAMEBOL.producto.entity.Unidad;
import com.CAMEBOL.producto.service.UnidadService;



@RestController
@RequestMapping("/unidad")
@CrossOrigin(origins = "*")
public class UnidadController {
	@Autowired
	private UnidadService unidadService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Unidad>> getAll(){
		List<Unidad> list = unidadService.getAll();
		return  new ResponseEntity<List<Unidad>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaEstado/{estado}")
	public ResponseEntity<List<Unidad>> getAllByEstado(@PathVariable("estado")String estado){
		List<Unidad> list = unidadService.getAllByEstado(estado);
		return  new ResponseEntity<List<Unidad>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaId/{id}")
	public ResponseEntity<Unidad> getById(@PathVariable("id") int id){
		if(!unidadService.existsById(id)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		Unidad unidad=unidadService.getById(id).get();
		return new ResponseEntity(unidad,HttpStatus.OK);
	}
	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody Unidad unidad, BindingResult bindingResult){
	
		unidadService.save(unidad);
        
		return new ResponseEntity(new Mensaje("Unidad creada"),HttpStatus.CREATED);
    }
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody Unidad unidad){

			
		Unidad uni=unidadService.getById(id).get();
		uni.setNombre(unidad.getNombre());
		uni.setProductos(unidad.getProductos());
		uni.setEstado(unidad.getEstado());
		uni.setFechaRegistro(unidad.getFechaRegistro());
		unidadService.save(uni);
		return new ResponseEntity(new Mensaje("Unidad Actualizada"),HttpStatus.OK);
	
	}	
	@DeleteMapping("/elimnar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		unidadService.delete(id);
		return new ResponseEntity(new Mensaje("Unidad Eliminada"),HttpStatus.OK);
	}
}
