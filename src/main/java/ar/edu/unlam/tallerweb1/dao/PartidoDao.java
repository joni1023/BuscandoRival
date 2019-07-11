package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Partido;

public interface PartidoDao {
	
	List<Partido> listaDePartidos();
	
	public void crearPartido (Partido partido);
	public void elmiminarPartido(Partido partido);
	public void modificarpertido(Partido partido);
	public List<Partido> listaPartidoEquipoID(Long id);

}