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

import com.CAMEBOL.producto.entity.ImagenProducto;
import com.CAMEBOL.producto.entity.Mensaje;
import com.CAMEBOL.producto.entity.Producto;
import com.CAMEBOL.producto.service.ImagenProductoService;
import com.CAMEBOL.producto.service.ProductoService;


@RestController
@RequestMapping("/imagenProducto")
@CrossOrigin(origins = "*")
public class ImagenProductoController {
	@Autowired
	private ImagenProductoService imagenProductoService;
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<ImagenProducto>> getAll(){
		List<ImagenProducto> list = imagenProductoService.getAll();
		return  new ResponseEntity<List<ImagenProducto>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaIdProducto/{id}")
	public ResponseEntity<List<ImagenProducto>> getByIdProducto(@PathVariable("id") int id){
		if(!productoService.existsById(id)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		Producto producto= productoService.getById(id).get();
		List<ImagenProducto> list = imagenProductoService.getByIdProducto(producto);
		return  new ResponseEntity<List<ImagenProducto>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaId/{id}")
	public ResponseEntity<ImagenProducto> getById(@PathVariable("id") int id){
		if(!imagenProductoService.existsById(id)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		ImagenProducto imagenProducto=imagenProductoService.getById(id).get();
		return new ResponseEntity(imagenProducto,HttpStatus.OK);
	}
	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody ImagenProducto imagenProducto, BindingResult bindingResult){
	
		imagenProductoService.save(imagenProducto);
        
		return new ResponseEntity(new Mensaje("Imagen creada"),HttpStatus.CREATED);
    }
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody ImagenProducto imagenProducto){

			
		ImagenProducto imgPro=imagenProductoService.getById(id).get();
		imgPro.setImgUrl(imagenProducto.getImgUrl());
		imgPro.setPrincipal(imagenProducto.getPrincipal());
		imgPro.setProducto(imagenProducto.getProducto());
		imgPro.setEstado(imagenProducto.getEstado());
		imgPro.setFechaRegistro(imagenProducto.getFechaRegistro());
		imagenProductoService.save(imgPro);
		return new ResponseEntity(new Mensaje("Imagen Actualizada"),HttpStatus.OK);
	
	}	
	@DeleteMapping("/elimnar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		if(!imagenProductoService.existsById(id)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		imagenProductoService.delete(id);
		return new ResponseEntity(new Mensaje("Imagen Eliminada"),HttpStatus.OK);
	}
}
