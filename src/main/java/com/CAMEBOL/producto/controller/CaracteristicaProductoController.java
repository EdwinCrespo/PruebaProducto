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

import com.CAMEBOL.producto.entity.CaracteristicaProducto;
import com.CAMEBOL.producto.entity.Mensaje;
import com.CAMEBOL.producto.entity.Producto;
import com.CAMEBOL.producto.service.CaracteristicaProductoService;
import com.CAMEBOL.producto.service.ProductoService;

@RestController
@RequestMapping("/caracteristicaProducto")
@CrossOrigin(origins = "*")
public class CaracteristicaProductoController {
	@Autowired
	private CaracteristicaProductoService caracteristicaProductoService;
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<CaracteristicaProducto>> getAll(){
		List<CaracteristicaProducto> list = caracteristicaProductoService.getAll();
		return  new ResponseEntity<List<CaracteristicaProducto>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaPorIdProducto/{idProducto}")
	public ResponseEntity<List<CaracteristicaProducto>> getByIdProducto(@PathVariable("idProducto") int idProducto){
		if(!productoService.existsById(idProducto)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		Producto producto= productoService.getById(idProducto).get();
		List<CaracteristicaProducto> list = caracteristicaProductoService.getByIdProducto(producto);
		return  new ResponseEntity<List<CaracteristicaProducto>>(list,HttpStatus.OK);
	}
	@GetMapping("/obtenerPorIdCaracteristicaProducto/{idCaracteristicaProducto}")
	public ResponseEntity<CaracteristicaProducto> getById(@PathVariable("idCaracteristicaProducto") int idCaracteristicaProducto){
		if(!caracteristicaProductoService.existsByIdCaracteristicaProducto(idCaracteristicaProducto)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		CaracteristicaProducto caracteristicaProducto=caracteristicaProductoService.getByIdCaracteristicaProducto(idCaracteristicaProducto).get();
		return new ResponseEntity(caracteristicaProducto,HttpStatus.OK);
	}
	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody CaracteristicaProducto caracteristicaProducto, BindingResult bindingResult){
	
		caracteristicaProductoService.save(caracteristicaProducto);
        
		return new ResponseEntity(new Mensaje("Caracteristica Producto creada"),HttpStatus.CREATED);
    }
	@PutMapping("/actualizar/{idCaracteristicaProducto}")
	public ResponseEntity<?> actualizar(@PathVariable("idCaracteristicaProducto") int idCaracteristicaProducto, @RequestBody CaracteristicaProducto caracteristicaProducto){

			
		CaracteristicaProducto carctProd=caracteristicaProductoService.getByIdCaracteristicaProducto(idCaracteristicaProducto).get();
		carctProd.setValor(caracteristicaProducto.getValor());
		carctProd.setCaracteristica(caracteristicaProducto.getCaracteristica());
		carctProd.setProducto(caracteristicaProducto.getProducto());
		caracteristicaProductoService.save(carctProd);
		return new ResponseEntity(new Mensaje("Imagen Actualizada"),HttpStatus.OK);
	
	}	
	@DeleteMapping("/elimnar/{idCaracteristicaProducto}")
	public ResponseEntity<?> delete(@PathVariable("idCaracteristicaProducto") int idCaracteristicaProducto){
		if(!caracteristicaProductoService.existsByIdCaracteristicaProducto(idCaracteristicaProducto)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		caracteristicaProductoService.delete(idCaracteristicaProducto);
		return new ResponseEntity(new Mensaje("Caracteristica producto Eliminada"),HttpStatus.OK);
	}
}
