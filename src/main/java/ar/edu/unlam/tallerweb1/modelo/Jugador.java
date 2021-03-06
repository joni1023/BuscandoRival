package ar.edu.unlam.tallerweb1.modelo;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Jugador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Integer edad;
	private Double peso;
	private Double altura;
	private String posicion;
	private Double valoracion;
	private Integer cantidadGoles;
	private Integer cantidadAmonestaciones;
	private Integer cantidadExpulsiones;
	
	public Jugador () {
		
	}
	
	public Jugador(Long id, String nombre, Integer edad, Date fechaDeNacimiento, Double peso, Double altura, Double valoracion) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.altura = altura;
		this.valoracion = valoracion;
	}
	
	
	public Integer getCantidadGoles() {
		return cantidadGoles;
	}

	public void setCantidadGoles(Integer cantidadGoles) {
		this.cantidadGoles = cantidadGoles;
	}

	public Integer getCantidadAmonestaciones() {
		return cantidadAmonestaciones;
	}

	public void setCantidadAmonestaciones(Integer cantidadAmonestaciones) {
		this.cantidadAmonestaciones = cantidadAmonestaciones;
	}

	public Integer getCantidadExpulsiones() {
		return cantidadExpulsiones;
	}

	public void setCantidadExpulsiones(Integer cantidadExpulsiones) {
		this.cantidadExpulsiones = cantidadExpulsiones;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getEdad() {
		return edad;
	}
	
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	public Double getValoracion() {
		return valoracion;
	}
	
	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}

	@ManyToOne
	Equipo equipo;
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
}