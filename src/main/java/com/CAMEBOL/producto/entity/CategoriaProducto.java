package com.CAMEBOL.producto.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CategoriaProducto")
public class CategoriaProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int idCategoriaProducto;
	 private String nombre;
	 private String descripcion;
	 private Date fechaRegistro;
	 private Date fechaActualizacion;
	 private String estado;
	 
	
	 @JsonIgnoreProperties(value = {"categoriaProducto"} , allowSetters = true)
	 @OneToMany(mappedBy = "categoriaProducto" , cascade = CascadeType.ALL)
	 private List<Producto> productos;

	 @PrePersist
		public void prePersist() throws ParseException {
			
			Date dt= new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fcF=formatter.format(dt);
			this.fechaRegistro=formatter.parse(fcF);
		}
		@PreUpdate
		public void preUpdate() throws ParseException {
			
			Date dt= new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fcF=formatter.format(dt);
			this.fechaActualizacion=formatter.parse(fcF);
		}
	 

	public CategoriaProducto() {
		productos=new ArrayList<>();
	}


	public int getIdCategoriaProducto() {
		return idCategoriaProducto;
	}


	public void setIdCategoriaProducto(int idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}


	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos.clear();
		productos.forEach(p->this.addProductos(p));
	}
	 
	public void addProductos(Producto producto) {
		this.productos.add(producto);
		producto.setCategoriaProducto(this);
	}
	
	 
	 
}
