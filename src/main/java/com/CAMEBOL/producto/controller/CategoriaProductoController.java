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

import com.CAMEBOL.producto.entity.CategoriaProducto;
import com.CAMEBOL.producto.entity.Mensaje;
import com.CAMEBOL.producto.service.CategoriaProductoService;



@RestController
@RequestMapping("/categoriaProducto")
@CrossOrigin(origins = "*")
public class CategoriaProductoController {
	@Autowired
	private CategoriaProductoService categoriaProductoService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<CategoriaProducto>> getAll(){
		List<CategoriaProducto> list = categoriaProductoService.getAll();
		return  new ResponseEntity<List<CategoriaProducto>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaEstado/{estado}")
	public ResponseEntity<List<CategoriaProducto>> getAllByEstado(@PathVariable("estado")String estado){
		List<CategoriaProducto> list = categoriaProductoService.getAllByEstado(estado);
		return  new ResponseEntity<List<CategoriaProducto>>(list,HttpStatus.OK);
	}
	@GetMapping("/listaId/{id}")
	public ResponseEntity<CategoriaProducto> getById(@PathVariable("id") int id){
		if(!categoriaProductoService.existsById(id)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		CategoriaProducto categoriaProducto=categoriaProductoService.getById(id).get();
		return new ResponseEntity(categoriaProducto,HttpStatus.OK);
	}
	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody CategoriaProducto categoriaProducto, BindingResult bindingResult){
	
		categoriaProductoService.save(categoriaProducto);
        
		return new ResponseEntity(new Mensaje("Categoria creada"),HttpStatus.CREATED);
    }
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody CategoriaProducto categoriaProducto){

			
		CategoriaProducto catProd=categoriaProductoService.getById(id).get();
		catProd.setNombre(categoriaProducto.getNombre());
		catProd.setDescripcion(categoriaProducto.getDescripcion());
		catProd.setProductos(categoriaProducto.getProductos());
		catProd.setEstado(categoriaProducto.getEstado());
		catProd.setFechaRegistro(categoriaProducto.getFechaRegistro());
		categoriaProductoService.save(catProd);
		return new ResponseEntity(new Mensaje("Categoria Actualizada"),HttpStatus.OK);
	
	}	
	@DeleteMapping("/elimnar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		categoriaProductoService.delete(id);
		return new ResponseEntity(new Mensaje("Categoria Eliminada"),HttpStatus.OK);
	}
}
