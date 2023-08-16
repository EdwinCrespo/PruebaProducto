package com.CAMEBOL.producto.service;

import java.util.List;
import java.util.Optional;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CAMEBOL.producto.entity.CategoriaProducto;
import com.CAMEBOL.producto.entity.Producto;
import com.CAMEBOL.producto.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {
	@Autowired
	ProductoRepository productoRepository;
	
	 public List<Producto> getAll(){
	    	
	    	return productoRepository.findAll();
	    }
	 public List<Producto> getByEmpresaAndEstado(int idEmpresa, String estado){
	    	
	    	return productoRepository.findByIdEmpresaAndEstado(idEmpresa, estado);
	    }
	 public List<Producto> getByEmpresa(int idEmpresa){
	    	
	    	return productoRepository.findByIdEmpresa(idEmpresa);
	    }
	 public List<Producto> getByCategoriaAndOrganizacionAndEstado(CategoriaProducto categoriaProducto,int idOrganizacion, String estado){
	    	
	    	return productoRepository.findByCategoriaProductoAndIdOrganizacionAndEstado(categoriaProducto,idOrganizacion, estado);
	    }
	 public List<Producto> getByEstado(String estado){
	    	
	    	return productoRepository.findByEstado(estado);
	    }
	 public Optional<Producto> getById(int id){
	        return productoRepository.findById(id);
	    }
	 public boolean existsById(Integer id){
	        return productoRepository.existsById(id);
	    }
	 public boolean existsByIdAndEstado(Integer id,String estado){
	        return productoRepository.existsByIdProductoAndEstado(id,estado);
	    }
	 public boolean existsByIdEmpresa(Integer idEmpresa){
	        return productoRepository.existsByIdEmpresa(idEmpresa);
	    }
	 public void save(Producto producto){
		 productoRepository.save(producto);
	    }
	  public void delete(int id) {
		  productoRepository.deleteById(id);
	    }

}
