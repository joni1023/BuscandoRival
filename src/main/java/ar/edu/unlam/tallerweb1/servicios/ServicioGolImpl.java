package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EquipoDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Gol;
import ar.edu.unlam.tallerweb1.modelo.Jugador;
//import ar.edu.unlam.tallerweb1.modelo.JugadorArquero;
//import ar.edu.unlam.tallerweb1.modelo.JugadorDeCampo;
import ar.edu.unlam.tallerweb1.modelo.Partido;

@Service("ServicioGol")
@Transactional
public class ServicioGolImpl implements ServicioGol {

	@Inject
	private EquipoDao equipoDao;

	@Override
	public Double promedioDeGol(Jugador jugador) {
		Double partidosJugados = (double) jugador.getEquipo().getListaDePartidos().size();
		Double golesConvertidos = 0.0;
		if (partidosJugados == 0 || partidosJugados == null) {
			return 0.0;
		} else {

			List<Gol> goles = jugador.getGoles();
			for (Gol gol : goles) {
				golesConvertidos += gol.getCantidad();
			}

			return golesConvertidos / partidosJugados;
		}
	}

	@Override
	public Double valoracionPorPromedioDeGol(Jugador jugador) {
		Double promedio = this.promedioDeGol(jugador);
		Double valoracion = promedio;
		// if(jugador.getClass()==JugadorDeCampo.class) {
		if (jugador.getPosicion().equals("Campo")) {
			if (promedio > 10.0) {
				valoracion = 10.0;
			}
			// } else if(jugador.getClass()==JugadorArquero.class) {
		} else if (jugador.getPosicion().equals("Arquero")) {
			valoracion = valoracion * 3;
			if (valoracion > 10.0) {
				valoracion = 10.0;
			}
		}
		return valoracion;
	}

	@Override
	public Double valoracionPorPromedioDeGolesEnContra(Jugador jugador) {
		Double promedioGolesEnContra = this.promedioDeGolesEnContra(jugador);
		Double valoracion = 0.0;
		if (promedioGolesEnContra == null) {
			return 0.0;
		} else if (promedioGolesEnContra >= 0.0 && promedioGolesEnContra <= 3.0) {
			valoracion = 10.0;
		} else if (promedioGolesEnContra > 3.0 && promedioGolesEnContra <= 5.0) {
			valoracion = 7.0;
		} else if (promedioGolesEnContra > 5.0 && promedioGolesEnContra <= 7.0) {
			valoracion = 5.0;
		} else {
			valoracion = 3.0;
		}

		return valoracion;
	}

	@Override
	public Double promedioDeGolesEnContra(Jugador jugador) {
		if (this.golesEnContra(jugador.getEquipo().getId()) == null) {
			return null;
		} else {
			Double golesEnContra = this.golesEnContra(jugador.getEquipo().getId());
			if (jugador.getEquipo().getListaDePartidos() == null) {
				return null;
			} else {
				Double partidosJugados = (double) jugador.getEquipo().getListaDePartidos().size();
				if (partidosJugados == 0.0) {
					return null;
				} else {
					return golesEnContra / partidosJugados;
				}
			}
		}
	}

	@Override
	public Double golesEnContra(Long id) {
		Equipo equipo = equipoDao.buscarEquipo(id);
		List<Partido> partidos = equipo.getListaDePartidos();
		Double golesEnContra = 0.0;
		if (partidos == null) {
			return null;

		} else {
			for (Partido partido : partidos) {
				if (equipo.equals(partido.getListaDeEquipos().get(0))) {
					List<Gol> goles = partido.getGolesEquipo2();
					if (goles == null){
						return 0.0;
					} else {
						for (Gol gol : goles) {
							golesEnContra += gol.getCantidad();
						}
					}
				} else if (equipo.equals(partido.getListaDeEquipos().get(1))) {
					List<Gol> goles = partido.getGolesEquipo1();
					if (goles == null) {
						return 0.0;
					} else {
						for (Gol gol : goles) {
							golesEnContra += gol.getCantidad();
						}
					}
				}
			}

			return golesEnContra;
		}
	}
}