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

import com.CAMEBOL.producto.entity.DetalleLote;
import com.CAMEBOL.producto.entity.Lote;
import com.CAMEBOL.producto.entity.Mensaje;
import com.CAMEBOL.producto.service.DetalleLoteService;
import com.CAMEBOL.producto.service.LoteService;



@RestController
@RequestMapping("/detalleProducto")
@CrossOrigin(origins = "*")
public class DetalleLoteController {
	@Autowired
	private DetalleLoteService detalleLoteService;
	@Autowired
	private LoteService loteService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<DetalleLote>> getAll(){
		List<DetalleLote> list = detalleLoteService.getAll();
		return  new ResponseEntity<List<DetalleLote>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaPorIdLote/{idLote}")
	public ResponseEntity<List<DetalleLote>> getByIdProducto(@PathVariable("idLote") int idLote){
		if(!loteService.existsByIdLote(idLote)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		Lote lote= loteService.getByIdLote(idLote).get();
		List<DetalleLote> list = detalleLoteService.getAllByLote(lote);
		return  new ResponseEntity<List<DetalleLote>>(list,HttpStatus.OK);
	}
	@GetMapping("/obtenerPorIdDetalleLote/{idDetalleLote}")
	public ResponseEntity<DetalleLote> getById(@PathVariable("idDetalleLote") int idDetalleLote){
		if(!detalleLoteService.existsByIdDetalleLote(idDetalleLote)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		DetalleLote detalleLote=detalleLoteService.getByIdDetalleLote(idDetalleLote).get();
		return new ResponseEntity(detalleLote,HttpStatus.OK);
	}
	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody DetalleLote detalleLote, BindingResult bindingResult){
	
		detalleLoteService.save(detalleLote);
        
		return new ResponseEntity(new Mensaje("Detalle Lote creado"),HttpStatus.CREATED);
    }
	@PutMapping("/actualizar/{idDetalleLote}")
	public ResponseEntity<?> actualizar(@PathVariable("idDetalleLote") int idDetalleLote, @RequestBody DetalleLote detalleLote){

			
		DetalleLote detLote=detalleLoteService.getByIdDetalleLote(idDetalleLote).get();
		detLote.setCantidad(detalleLote.getCantidad());
		detLote.setEstado(detalleLote.getEstado());
		detLote.setFechaIngreso(detalleLote.getFechaIngreso());
		detLote.setFechaRegistro(detalleLote.getFechaRegistro());
		detLote.setIdAlmacen(detalleLote.getIdAlmacen());
		detLote.setPorcentajeGanancia(detalleLote.getPorcentajeGanancia());
		detLote.setPorcentajeDescuento(detalleLote.getPorcentajeDescuento());
		detLote.setPrecioCompra(detalleLote.getPrecioCompra());
		detLote.setPrecioVenta(detalleLote.getPrecioVenta());
		detLote.setLote(detalleLote.getLote());
		detLote.setProducto(detalleLote.getProducto());
		return new ResponseEntity(new Mensaje("Detalle Lote Actualizado"),HttpStatus.OK);
	
	}	
	@DeleteMapping("/elimnar/{idDetalleLote}")
	public ResponseEntity<?> delete(@PathVariable("idDetalleLote") int idDetalleLote){
		if(!detalleLoteService.existsByIdDetalleLote(idDetalleLote)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		detalleLoteService.delete(idDetalleLote);
		return new ResponseEntity(new Mensaje("Detalle Lote Eliminado"),HttpStatus.OK);
	}
}
