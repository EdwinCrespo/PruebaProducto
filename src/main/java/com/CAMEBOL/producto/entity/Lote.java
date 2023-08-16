package com.CAMEBOL.producto.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Lote")
public class Lote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLote;
	private int idEmpresa;
	private String codigoLote;
	private int cantidadProductos;
	private Date fechaFabricacion;
	private Date fechaVencimiento;
	private String descripcion;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private String estado;
	
	 @JsonIgnoreProperties(value = {"lote"} , allowSetters = true)
	  @OneToMany( mappedBy = "lote", cascade = CascadeType.ALL , fetch = FetchType.LAZY )  
	   private List<DetalleLote> detalleLotes = new ArrayList<>();

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
	 
	 
	public Lote() {
		super();
	}

	public int getIdLote() {
		return idLote;
	}

	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public List<DetalleLote> getDetalleLotes() {
		return detalleLotes;
	}

	public void setDetalleLotes(List<DetalleLote> detalleLotes) {
		this.detalleLotes = detalleLotes;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}

	public int getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	public Date getFechafabricacion() {
		return fechaFabricacion;
	}

	public void setFechafabricacion(Date fechafabricacion) {
		this.fechaFabricacion = fechafabricacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	 
	 
}
