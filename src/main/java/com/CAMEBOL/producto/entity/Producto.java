	package com.CAMEBOL.producto.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	private String nombre;
	private String codigo;
	private Double precioCosto;
	private Double precioVenta;
	private Double stockTotal;
	private Double stockRestante;
	private Double stockMinimo;
	private String descripcion;
	private int idOrganizacion;
	private int idEmpresa;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private String estado;

	@JsonIgnoreProperties(value = { "producto" }, allowSetters = true)
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ImagenProducto> imagenesProducto;
	
	 @JsonIgnoreProperties(value = {"productos"} ,allowSetters = true)
	 @ManyToOne()
	 @JoinColumn(name = "idCategoriaProducto")
	 private CategoriaProducto categoriaProducto;
	 
	 
	 @JsonIgnoreProperties(value = {"productos"} ,allowSetters = true)
	 @ManyToOne()
	 @JoinColumn(name = "idUnidad")
	 private Unidad unidad;
	 
	 @JsonIgnoreProperties(value = {"producto"} , allowSetters = true)
	  @OneToMany( mappedBy = "producto", cascade = CascadeType.ALL , fetch = FetchType.LAZY)  
	   private List<CaracteristicaProducto> caracteristicaProductos = new ArrayList<>();
	 
	 @JsonIgnoreProperties(value = {"producto"} , allowSetters = true)
	  @OneToMany( mappedBy = "producto", cascade = CascadeType.ALL , fetch = FetchType.LAZY)  
	   private List<DetalleLote> detalleLotes = new ArrayList<>();
	 

	public Producto() {
		this.imagenesProducto=new ArrayList<>();
		this.caracteristicaProductos= new ArrayList<>();
	}
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
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(Double precioCosto) {
		this.precioCosto = precioCosto;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(int idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
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

	public List<ImagenProducto> getImagenesProducto() {
		return imagenesProducto;
	}

	public void setImagenesProducto(List<ImagenProducto> imagenesProducto) {
		this.imagenesProducto.clear();
		imagenesProducto.forEach(p-> this.addImagen(p));
	}
	public void addImagen(ImagenProducto imagenProducto) {
		this.imagenesProducto.add(imagenProducto);
		imagenProducto.setProducto(this);
	}
	public CategoriaProducto getCategoriaProducto() {
		return categoriaProducto;
	}
	public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}
	public Unidad getUnidad() {
		return unidad;
	}
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	public List<CaracteristicaProducto> getCaracteristicaProductos() {
		return caracteristicaProductos;
	}
	public void setCaracteristicaProductos(List<CaracteristicaProducto> caracteristicaProductos) {
		this.caracteristicaProductos.clear();
		caracteristicaProductos.forEach(p-> this.addProducto(p));
	}
	public void addProducto(CaracteristicaProducto caracteristicaProducto) {
		this.caracteristicaProductos.add(caracteristicaProducto);
		caracteristicaProducto.setProducto(this);
	}
	public List<DetalleLote> getDetalleLotes() {
		return detalleLotes;
	}
	public void setDetalleLotes(List<DetalleLote> detalleLotes) {
		this.detalleLotes = detalleLotes;
	}
	public Double getStockTotal() {
		return stockTotal;
	}
	public void setStockTotal(Double stockTotal) {
		this.stockTotal = stockTotal;
	}
	public Double getStockRestante() {
		return stockRestante;
	}
	public void setStockRestante(Double stockRestante) {
		this.stockRestante = stockRestante;
	}
	public Double getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(Double stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	
	
	
	

}
