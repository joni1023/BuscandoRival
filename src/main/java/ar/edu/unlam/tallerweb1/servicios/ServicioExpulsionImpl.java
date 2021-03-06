package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ExpulsionDao;
import ar.edu.unlam.tallerweb1.dao.JugadorDao;
import ar.edu.unlam.tallerweb1.dao.PartidoDao;
import ar.edu.unlam.tallerweb1.modelo.Expulsion;
import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.Partido;

@Service("ServicioExpulsion")
@Transactional
public class ServicioExpulsionImpl implements ServicioExpulsion {
	
	@Inject
	JugadorDao jugadorDao;
	
	@Inject
	PartidoDao partidoDao;
	
	@Inject
	ExpulsionDao expulsionDao;
	
	@Override
	public Integer cantidadExpulsiones(Long id) {
		Jugador miJugador = jugadorDao.buscarJugador(id);
		List<Expulsion> expulsiones = expulsionDao.expulsionesJugador(miJugador);
		Integer cantidadExpulsiones = 0;
		if(expulsiones.isEmpty()) {
			return 0;
		} else {
			cantidadExpulsiones = expulsiones.size();
			return cantidadExpulsiones;
		}
	}

	@Override
	public Double promedioExpulsiones(Jugador jugador) {
		List<Partido> listaDePartidos = partidoDao.listaDePartidos();	
		Integer cantidadExpulsiones = this.cantidadExpulsiones(jugador.getId());
		Double partidosJugados = 0.0;
	
		for (Partido partido : listaDePartidos) {
			if (partido.getLocal().equals(jugador.getEquipo())
					|| partido.getVisitante().equals(jugador.getEquipo())) {
				partidosJugados++;
			}
		}
		if(partidosJugados == 0.0) {
			return 0.0;
		} else {
			return (Double) (cantidadExpulsiones / partidosJugados);
		}
	}

	@Override
	public Double valoracionPorExpulsiones(Jugador jugador) {
		Double promedioExpulsiones = this.promedioExpulsiones(jugador);
		Double valoracion = promedioExpulsiones * (-10);
		
		return valoracion;
	}

	@Override
	public void guardarExpulsion(Expulsion expulsion) {
		expulsionDao.guardarExpulsion(expulsion);
	}
}