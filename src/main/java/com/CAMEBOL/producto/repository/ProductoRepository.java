package com.CAMEBOL.producto.repository;

import java.util.List;

import javax.swing.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CAMEBOL.producto.entity.CategoriaProducto;
import com.CAMEBOL.producto.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
		List<Producto> findByIdEmpresaAndEstado(Integer idEmpresa, String estado);
		List<Producto> findByIdEmpresa(Integer idEmpresa);
		List<Producto> findByCategoriaProductoAndIdOrganizacionAndEstado(CategoriaProducto categoriaProducto,Integer idOrganizacion, String estado);
		Boolean existsByIdEmpresa(Integer idEmpresa);
		Boolean existsByIdProductoAndEstado(Integer idEmpresa,String estado);
		List<Producto> findByEstado(String estado);
}
