package ar.edu.unlam.tallerweb1.modelo;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Equipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private int valoracion;
	private int golesAFavor;
	private int golesEnContra;
	private int partidosGanados;
	private int partidosPerdidos;
	private int partidosEmpatados;
	private Double promedioDeEdad;
	private String categoria;
	private String localidad;
	
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
	
	@OneToMany
	List <Jugador> listaDeJugadores;
	@OneToMany
	List <Partido> listaDePartidos;

	public List<Partido> getListaDePartidos() {
		return listaDePartidos;
	}
	public void setListaDePartidos(List<Partido> listaDePartidos) {
		this.listaDePartidos = listaDePartidos;
	}
	public List<Jugador> getListaDeJugadores() {
		return listaDeJugadores;
	}
	public void setListaDeJugadores(List<Jugador> listaDeJugadores) {
		this.listaDeJugadores = listaDeJugadores;
	}
	
	public Double promedioDeEdad() {
		Double edadTotal = 0.0;
		for (Jugador jugador : listaDeJugadores) {
			edadTotal += jugador.getEdad();
		}
		Double promedio = (edadTotal / this.listaDeJugadores.size());
		
		return promedio;
	}
	
	
}