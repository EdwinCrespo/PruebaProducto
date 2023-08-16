package com.CAMEBOL.producto.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="ImagenProducto")
public class ImagenProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int idImagenProducto;
	@JsonIgnoreProperties(value = {"imagenesProducto"} , allowSetters = true)
	@ManyToOne
	@JoinColumn(name = "idProducto")
	 private Producto producto;
	@Column(columnDefinition="TEXT")
	@Lob
	 private String imgUrl;
	 private Boolean principal;
	 private Date fechaRegistro;
	 private Date fechaActualizacion;
	 private String estado;
	 
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
	 
	public ImagenProducto() {
		super();
	}
	
	public int getIdImagenProducto() {
		return idImagenProducto;
	}
	public void setIdImagenProducto(int idImagenProducto) {
		this.idImagenProducto = idImagenProducto;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
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
	 
	 
	 
	 
}
