package com.CAMEBOL.producto.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "DetalleLote")
public class DetalleLote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalleLote;
	private int idAlmacen;
	private Date fechaIngreso;
	private Double cantidad;
	private Double precioCompra;
	private Double precioVenta;
	private Double porcentajeGanancia;
	private Double porcentajeDescuento;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private String estado;
	
	 @JsonIgnoreProperties(value = {"detalleLotes"} , allowSetters = true)
	 @ManyToOne
	 @JoinColumn(name = "idProducto")
	 private Producto producto;
	 
	 @JsonIgnoreProperties(value = {"detalleLotes"} , allowSetters = true)
	 @ManyToOne
	 @JoinColumn(name = "idLote")
	    private Lote lote;

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
	 
	public DetalleLote() {
		super();
	}

	public int getIdDetalleLote() {
		return idDetalleLote;
	}

	public void setIdDetalleLote(int idDetalleLote) {
		this.idDetalleLote = idDetalleLote;
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Double getPorcentajeGanancia() {
		return porcentajeGanancia;
	}

	public void setPorcentajeGanancia(Double porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}

	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}
	 
	 
}
