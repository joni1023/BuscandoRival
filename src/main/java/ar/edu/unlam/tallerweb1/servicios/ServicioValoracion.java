package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.ValoracionAltura;
import ar.edu.unlam.tallerweb1.modelo.ValoracionEdad;
import ar.edu.unlam.tallerweb1.modelo.ValoracionPesoAlturaA;

public interface ServicioValoracion {
	
	public List<ValoracionEdad> verValoraciondeEdadTabla();
	
	public List<ValoracionAltura> verValoracionAlturaTabla();
	
	public List<ValoracionPesoAlturaA> listaValoracionPesoAlturaA();
	
	public ValoracionPesoAlturaA verValoracionPesoAlturaArqTabla();
	
	//valores de jugadores
	public Double valoracionPorEdad(Jugador jugador);

	public Double valoracionPorAltura(Jugador jugador);
	
	public Double valoracionPorAlturaYPeso(Jugador jugador);
	
	//prueba
	public List<ValoracionPesoAlturaA> listaValoracionPesoAlturATipo(String tipo);

	
	

}
