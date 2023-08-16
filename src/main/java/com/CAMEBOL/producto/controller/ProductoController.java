package com.CAMEBOL.producto.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.Spring;
import javax.validation.Valid;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.CAMEBOL.producto.entity.CategoriaProducto;
import com.CAMEBOL.producto.entity.ImagenProducto;
import com.CAMEBOL.producto.entity.Mensaje;
import com.CAMEBOL.producto.entity.Producto;
import com.CAMEBOL.producto.entity.Unidad;
import com.CAMEBOL.producto.service.CategoriaProductoService;
import com.CAMEBOL.producto.service.ProductoService;


@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	@Autowired
	private CategoriaProductoService categoriaProductoService;
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Producto>> getAll(){
		List<Producto> list = productoService.getAll();
		return  new ResponseEntity<List<Producto>>(list,HttpStatus.OK);
	}
	@GetMapping("/obtenerProductoPorId/{id}")
	public ResponseEntity<Producto> getProductoByIdProducto(@PathVariable("id") int id){
		if(!productoService.existsById(id)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		Producto producto=productoService.getById(id).get();
		return new ResponseEntity(producto,HttpStatus.OK);
	}
	@GetMapping("/obtenerProductosPorIdEmpresaEstado/{id}/{estado}")
	public ResponseEntity<List<Producto>> getByidEmpresaEstado(@PathVariable("id") int idEmpresa,@PathVariable("estado") String estado){
//		Boolean empresa= restTemplate.getForObject("http://localhost:8081/empresa/existe/"+idEmpresa, Boolean.TYPE);
//		if(empresa) {
//			List<Producto> list = productoService.getByEmpresaAndEstado(idEmpresa,estado);
//			return  new ResponseEntity<List<Producto>>(list,HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.OK);
//		}
		
		if(!productoService.existsByIdEmpresa(idEmpresa)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		List<Producto> list = productoService.getByEmpresaAndEstado(idEmpresa,estado);
		return  new ResponseEntity<List<Producto>>(list,HttpStatus.OK);
		
	}
	@GetMapping("/obtenerProductosPorIdEmpresa/{id}")
	public ResponseEntity<List<Producto>> getByidEmpresa(@PathVariable("id") int idEmpresa){

		if(!productoService.existsByIdEmpresa(idEmpresa)) {
			Mensaje msg=new Mensaje();
			msg.setMensaje("El id no existe");
			msg.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
		}
		List<Producto> list = productoService.getByEmpresa(idEmpresa);
		return  new ResponseEntity<List<Producto>>(list,HttpStatus.OK);
		
	}
	@GetMapping("/obtenerProductosPorEstado/{estado}")
	public ResponseEntity<List<Producto>> getByEstado(@PathVariable("estado") String estado){
		
		List<Producto> list = productoService.getByEstado(estado);
		return  new ResponseEntity<List<Producto>>(list,HttpStatus.OK);
	}
	@GetMapping("/ObtenerProductosPorIdCategoriaIdOrganizacionEstado/{idCategoria}/{idOrganizacion}/{estado}")
	public ResponseEntity<List<Producto>> getByCategoriaOrganizacionEstado(@PathVariable("idCategoria") Integer idCategoria,@PathVariable("idOrganizacion") Integer idOrganizacion,@PathVariable("estado") String estado){
		if(!categoriaProductoService.existsById(idCategoria)) {
			return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
		}
		CategoriaProducto categoriaProducto=categoriaProductoService.getById(idCategoria).get();
		List<Producto> list = productoService.getByCategoriaAndOrganizacionAndEstado(categoriaProducto,idOrganizacion,estado);
		return  new ResponseEntity<List<Producto>>(list,HttpStatus.OK);
	}
	

	
	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody Producto producto, BindingResult bindingResult){
	
		productoService.save(producto);
		Mensaje response = new Mensaje(); 
        response.setStatus(HttpStatus.CREATED.value());
        response.setMensaje("Producto creado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody Producto producto){

			
		Producto prod=productoService.getById(id).get();
		prod.setCodigo(producto.getCodigo());
		prod.setDescripcion(producto.getDescripcion());
		prod.setIdEmpresa(producto.getIdEmpresa());
		prod.setIdOrganizacion(producto.getIdOrganizacion());
		prod.setNombre(producto.getNombre());
		prod.setPrecioCosto(producto.getPrecioCosto());
		prod.setPrecioVenta(producto.getPrecioVenta());
		prod.setStockTotal(producto.getStockTotal());
		prod.setStockRestante(producto.getStockRestante());
		prod.setStockMinimo(producto.getStockMinimo());
		prod.setCategoriaProducto(producto.getCategoriaProducto());
		prod.setUnidad(producto.getUnidad());
		prod.setEstado(producto.getEstado());
		prod.setFechaRegistro(producto.getFechaRegistro());
		prod.setCaracteristicaProductos(producto.getCaracteristicaProductos());
		prod.setDetalleLotes(producto.getDetalleLotes());
		prod.setImagenesProducto(producto.getImagenesProducto());
		productoService.save(prod);
		return new ResponseEntity(new Mensaje("Producto Actualizado"),HttpStatus.OK);
	
	}
	@PutMapping("/actualizarStock/{id}/{cantidad}")
	public ResponseEntity<?> actualizarStock(@PathVariable("id") int id,@PathVariable("cantidad") Double cantidad){
		   Optional<Producto> optionalProducto = productoService.getById(id);
		   if (optionalProducto.isPresent()) {
			   Producto prod = optionalProducto.get();
			    
			    if (prod.getStockRestante() < cantidad) {
			        return new ResponseEntity(new Mensaje("Stock Insuficiente"), HttpStatus.BAD_REQUEST);
			    }
			    
			    double stockNuevo = prod.getStockRestante() - cantidad;
			    prod.setStockRestante(stockNuevo);
			    productoService.save(prod);
			    
			    return new ResponseEntity(new Mensaje("Stock Actualizado"), HttpStatus.OK);
			} else {
			    return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
			}
		}
	
    // Existe el producto y es valido su estado es 1
    @GetMapping("/checkProductoPorIdProductoEstado/{idProducto}")
    public boolean checkExisteProductoEstadoValido(@PathVariable("idProducto") int idProducto) {
        boolean existe = productoService.existsByIdAndEstado(idProducto,"1");
             if (existe) {
                 return true;
             } 
             else {
                 return false;
             }
      
       
    }
    
    // Cuenta con la cantidad necesaria para hacer la compra
    @GetMapping("/checkCantidadProducto/{idProducto}/{cantidad}")
    public boolean checkCantidadProducto(@PathVariable("idProducto") int idProducto,@PathVariable("cantidad") Double cantidad) {
    	 Optional<Producto> productoOptional = productoService.getById(idProducto);
         
         if (productoOptional.isPresent()) {
         	 if(productoOptional.get().getStockRestante()>=cantidad && cantidad>0)
              {
         		return true;
              }
         	else {
                
                return false;
            }
              
         } 
         else {
            
             return false;
         }
      
       
    }
}
