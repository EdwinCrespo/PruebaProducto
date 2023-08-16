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

import com.CAMEBOL.producto.entity.Lote;
import com.CAMEBOL.producto.entity.Mensaje;
import com.CAMEBOL.producto.service.LoteService;


@RestController
@RequestMapping("/lote")
@CrossOrigin(origins = "*")
public class LoteController {
	@Autowired
	private LoteService loteService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Lote>> getAll(){
		List<Lote> list = loteService.getAll();
		return  new ResponseEntity<List<Lote>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaPorEmpresaEstado/{idEmpresa}/{estado}")
	public ResponseEntity<List<Lote>> getAllByEstado(@PathVariable("idEmpresa")int idEmpresa,@PathVariable("estado")String estado){
		List<Lote> list = loteService.getByIdEmpresaAndEstado(idEmpresa,estado);
		return  new ResponseEntity<List<Lote>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaId/{id}")
	public ResponseEntity<Lote> getById(@PathVariable("id") int id){
		if(!loteService.existsByIdLote(id)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		Lote lote=loteService.getByIdLote(id).get();
		return new ResponseEntity(lote,HttpStatus.OK);
	}
	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody Lote lote, BindingResult bindingResult){
	
		loteService.save(lote);
        
		return new ResponseEntity(new Mensaje("Lote creado"),HttpStatus.CREATED);
    }
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody Lote lote){

			
		Lote lot=loteService.getByIdLote(id).get();
		lot.setCantidadProductos(lote.getCantidadProductos());
		lot.setCodigoLote(lote.getCodigoLote());
		lot.setDescripcion(lote.getDescripcion());
		lot.setEstado(lote.getEstado());
		lot.setFechaRegistro(lote.getFechaRegistro());
		lot.setFechafabricacion(lote.getFechafabricacion());
		lot.setFechaVencimiento(lote.getFechaVencimiento());
		lot.setIdEmpresa(lote.getIdEmpresa());
		lot.setDetalleLotes(lote.getDetalleLotes());
		loteService.save(lot);
		return new ResponseEntity(new Mensaje("Lote Actualizado"),HttpStatus.OK);
	
	}	
	@DeleteMapping("/elimnar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		loteService.delete(id);
		return new ResponseEntity(new Mensaje("Lote Eliminado"),HttpStatus.OK);
	}
}
