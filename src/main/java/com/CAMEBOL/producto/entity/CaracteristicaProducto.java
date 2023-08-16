package com.CAMEBOL.producto.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CaracteristicaProducto")
public class CaracteristicaProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCaracteristicaProducto;
	
	 @JsonIgnoreProperties(value = {"caracteristicaProductos"} , allowSetters = true)
	 @ManyToOne
	 @JoinColumn(name = "idProducto")
	    private Producto producto;
	 @JsonIgnoreProperties(value = {"caracteristicaProductos"} , allowSetters = true)
	 @ManyToOne
	 @JoinColumn(name = "idCaracteristica")
	    private Caracteristica caracteristica;

    private String valor;

    
    
    
	public CaracteristicaProducto() {

	}

	public int getIdCaracteristicaProducto() {
		return idCaracteristicaProducto;
	}

	public void setIdCaracteristicaProducto(int idCaracteristicaProducto) {
		this.idCaracteristicaProducto = idCaracteristicaProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
    
    
}
