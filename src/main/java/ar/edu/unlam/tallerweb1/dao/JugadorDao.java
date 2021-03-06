package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Jugador;

public interface JugadorDao {
	
	public List <Jugador> listaDeJugadores();
	
	public Jugador buscarJugador(Long id);
	
	public List <Jugador> listaDeJugadoresPorEquipo(Long idDeEquipo);
	
	public void agregarJugador(Jugador jugador);
	
	public void editarJugador(Jugador jugador);
	
	public void eliminarJugador(Jugador jugador);
}