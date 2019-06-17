package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.JugadorDao;
import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.JugadorArquero;
import ar.edu.unlam.tallerweb1.modelo.JugadorDeCampo;

@Service("ServicioJugador")
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {

	@Inject
	private JugadorDao servicioJugadorDao;
	@Inject
	private ServicioGol servicioGol;
	@Inject
	private ServicioAmonestacion servicioAmonestacion;
	@Inject
	private ServicioExpulsion servicioExpulsion;
	
	@Override
	public List<Jugador> listarJugadores() {
		
		return servicioJugadorDao.listaDeJugadores();
	}

	// VALORACION DE UN ARQUERO.
	@Override
	public Double valoracionJugador(Long id) {
		Jugador miJugador = servicioJugadorDao.buscarJugador(id);
		Double valoracion = 0.0;
		if(miJugador.getClass() == JugadorArquero.class) {
			
		Double valorEdad = this.valoracionPorEdad(miJugador);
		Double valorAltura = this.valoracionPorAltura(miJugador);
		Double valorPesoAltura = this.valoracionPorAlturaYPeso(miJugador);
		Double valorPromedioDeGol = servicioGol.valoracionPorPromedioDeGol(miJugador);
		Double valorGolesRecibidos = servicioGol.valoracionPorPromedioDeGolesEnContra(miJugador);
		Double valorPorPorteriaImbatible = servicioGol.valoracionPorteriaImbatible(miJugador);
		Double valorPorAmonestaciones = servicioAmonestacion.valoracionPorAmonestaciones(miJugador);
		Double valorPorExpulsiones = servicioExpulsion.valoracionPorExpulsiones(miJugador);
		
		valoracion = valorEdad + valorAltura + valorPesoAltura + valorGolesRecibidos + valorPorPorteriaImbatible + valorPromedioDeGol
				+ valorPorAmonestaciones + valorPorExpulsiones;
		
		}else if(miJugador.getClass() == JugadorDeCampo.class) {
			
			Double valorEdad = this.valoracionPorEdad(miJugador);
			Double valorPromedioDeGol = servicioGol.valoracionPorPromedioDeGol(miJugador);
			Double valorAltura = this.valoracionPorAltura(miJugador);
			Double valorAlturaPeso = this.valoracionPorAlturaYPeso(miJugador);
			Double valorPorAmonestaciones = servicioAmonestacion.valoracionPorAmonestaciones(miJugador);
			Double valorPorExpulsiones = servicioExpulsion.valoracionPorExpulsiones(miJugador);
			
			valoracion= valorEdad + valorPromedioDeGol + valorAltura + valorAlturaPeso + valorPorAmonestaciones + valorPorExpulsiones;
		}
		return valoracion;
	}

	@Override
	public Double valoracionPorEdad(Jugador jugador) {
		Double valoracion = 0.0;
		if(jugador.getClass() == JugadorArquero.class) {
		if(jugador.getEdad() < 20) {
			valoracion = 8.0;
			
		} else if(jugador.getEdad() >= 20 && jugador.getEdad() <= 25) {
			valoracion = 9.0;
			
		} else if (jugador.getEdad() > 25 && jugador.getEdad() <= 30) {
			valoracion = 10.0;
			
		} else if(jugador.getEdad() > 30 && jugador.getEdad() <= 35) {
			valoracion = 8.0;
		}
		// Deberia tomar mayor a 35 a�os.
		valoracion = 5.0;
		
		
		}else if(jugador.getClass() == JugadorDeCampo.class) {
			if(jugador.getEdad() < 20) {
				valoracion = 8.0;
				
			} else if(jugador.getEdad() >= 20 && jugador.getEdad() <= 25) {
				valoracion = 9.0;
				
			} else if (jugador.getEdad() > 25 && jugador.getEdad() <= 30) {
				valoracion = 10.0;
				
			} else if(jugador.getEdad() > 30 && jugador.getEdad() <= 35) {
				valoracion = 8.0;
			}
			
			// Deberia tomar mayor a 35 a�os.
			valoracion = 5.0;
			
		}
		return valoracion;
	}

	@Override
	public Double valoracionPorAltura(Jugador jugador) {
		Double valoracion = 0.0;
		if(jugador.getClass() == JugadorArquero.class) {
		if(jugador.getAltura() < 1.70) {
			valoracion = 5.0;
			
		} else if(jugador.getAltura() >= 1.70 && jugador.getAltura() <= 1.85) {
			valoracion = 7.5;
			
		} else{
			valoracion =  10.0;
		}

		}else if (jugador.getClass() == JugadorDeCampo.class) {
			if(jugador.getAltura() <= 1.60) {
				valoracion = 5.0;
			}else if(jugador.getAltura() > 1.60 && jugador.getAltura() <= 1.75) {
				valoracion = 7.5;
			}else {
				valoracion  = 10.0;
			}
		}
		return valoracion;
	}

	@Override
	public Double valoracionPorAlturaYPeso(Jugador jugador) {
		
		Double valor = 0.0;
		if(jugador.getClass() == JugadorArquero.class) {
		if(jugador.getAltura() <= 1.70) {
			if(jugador.getPeso() < 60.0) {
				valor = 4.0;
				
			} else if(jugador.getPeso() >= 60.0 && jugador.getPeso() <= 75.0) {
				valor = 6.0;
				
			} else if(jugador.getPeso() > 75.0) {
				valor = 4.0;
			}
			/*========================================*/
		} else if(jugador.getAltura() > 1.70 && jugador.getAltura() <= 1.85){
			if(jugador.getPeso() < 60.0) {
				valor = 7.0;
				
			} else if(jugador.getPeso() >= 60.0 && jugador.getPeso() <= 70.0) {
				valor = 9.0;
				
			} else if(jugador.getPeso() >= 70.0 && jugador.getPeso() <= 80.0) {
				valor = 8.0;
				
			} else if(jugador.getPeso() > 80) {
				valor = 7.0;
			}
			/*========================================*/
		} else if (jugador.getAltura() > 1.85 && jugador.getAltura() <= 1.90) {
			if(jugador.getPeso() < 70.0) {
				valor = 8.5;
				
			} else if(jugador.getPeso() >= 70.0 && jugador.getPeso() <= 90.0) {
				valor = 10.0;
				
			} else if(jugador.getPeso() >= 90.0 && jugador.getPeso() <= 100.0) {
				valor = 8.0;
				
			} else if(jugador.getPeso() > 100) {
				valor = 6.0;
			}else {
				if(jugador.getPeso()<70.0) {
					valor = 4.0;
				}else if(jugador.getPeso() >=70 && jugador.getPeso() <= 90.0) {
					valor = 6.0;
				}else if (jugador.getPeso() > 90 && jugador.getPeso() <= 100) {
					valor = 8.0;
				}else if(jugador.getPeso() > 100){
					valor = 4.0;
				}
			}
		}
	}else if(jugador.getClass() == JugadorDeCampo.class) {
		if(jugador.getAltura() <= 1.60) {
			if(jugador.getPeso() < 60.0) {
				valor = 4.0;
				
			} else if(jugador.getPeso() >= 60.0 && jugador.getPeso() <= 75.0) {
				valor = 6.0;
				
			} else if(jugador.getPeso() > 75.0) {
				valor = 4.0;
			}
			/*========================================*/
		} else if(jugador.getAltura() > 1.60 && jugador.getAltura() <= 1.75){
			if(jugador.getPeso() < 60.0) {
				valor = 7.0;
				
			} else if(jugador.getPeso() >= 60.0 && jugador.getPeso() <= 70.0) {
				valor = 9.0;
				
			} else if(jugador.getPeso() >= 70.0 && jugador.getPeso() <= 80.0) {
				valor = 8.0;
				
			} else if(jugador.getPeso() > 80) {
				valor = 7.0;
			}
			/*========================================*/
		} else{
			if(jugador.getPeso() < 70.0) {
				valor = 8.5;
				
			} else if(jugador.getPeso() >= 70.0 && jugador.getPeso() <= 90.0) {
				valor = 10.0;
				
			} else if(jugador.getPeso() >= 90.0 && jugador.getPeso() <= 100.0) {
				valor = 8.0;
				
			} else if(jugador.getPeso() > 100) {
				valor = 6.0;
			
			}
		}
	}
			/*========================================*/
		
		
		 return valor;	
	}
	
}