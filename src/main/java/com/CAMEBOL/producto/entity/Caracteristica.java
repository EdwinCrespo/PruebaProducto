package com.CAMEBOL.producto.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Caracteristica")
public class Caracteristica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCaracteristica;
	private String nombre;
	private String descripcion;	
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private String estado;
	
	
	 @JsonIgnoreProperties(value = {"caracteristica"} , allowSetters = true)
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "caracteristica")  
	 private List<CaracteristicaProducto> caracteristicaProductos = new ArrayList<>();

	 @PrePersist
		public void prePersist() throws ParseException {

			Date dt = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fcF = formatter.format(dt);
			this.fechaRegistro = formatter.parse(fcF);
		}

		@PreUpdate
		public void preUpdate() throws ParseException {

			Date dt = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fcF = formatter.format(dt);
			this.fechaActualizacion = formatter.parse(fcF);
		}

	public Caracteristica() {
		this.caracteristicaProductos=new ArrayList<>();
	}



	public int getIdCaracteristica() {
		return idCaracteristica;
	}



	public void setIdCaracteristica(int idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
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



	public List<CaracteristicaProducto> getCaracteristicaProductos() {
		return caracteristicaProductos;
	}



	public void setCaracteristicaProductos(List<CaracteristicaProducto> caracteristicaProductos) {
		this.caracteristicaProductos = caracteristicaProductos;
	}
		 

}
