package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.ValoracionAltura;
import ar.edu.unlam.tallerweb1.modelo.ValoracionEdad;
import ar.edu.unlam.tallerweb1.modelo.ValoracionPesoAltura;
import ar.edu.unlam.tallerweb1.modelo.ValoracionesGenerales;

public interface ServicioValoracion {
	
	public List<ValoracionEdad> verValoraciondeEdadTabla();
	
	public List<ValoracionAltura> verValoracionAlturaTabla();
	
	public List<ValoracionPesoAltura> listaValoracionPesoAltura();
	
	public ValoracionPesoAltura verValoracionPesoAlturaArqTabla();
	
	// Valores de jugadores
	public Double valoracionPorEdad(Jugador jugador);

	public Double valoracionPorAltura(Jugador jugador);
	
	public Double valoracionPorAlturaYPeso(Jugador jugador);
	
	// Prueba
	public List<ValoracionPesoAltura> listaValoracionPesoAlturaTipo(String tipo);
	
	public Integer valoracionGeneralRadio();
	
	// Admin
	public ValoracionEdad trearValoracionEdadPorId(Long id);
	public void modificarTablaEdad(ValoracionEdad nuevaValoracion);
	public ValoracionAltura traerValoracionAlturaPorID(Long id);
	public void modificarTablaAltura(ValoracionAltura nuevaValoracion);
	public ValoracionPesoAltura traerValoracionPesoAlturaPorID(Long id);
	public void modificarTablaPesoAltura(ValoracionPesoAltura nuevaValoracion);
	public ValoracionesGenerales traerVAloracionesGenerales();
	public void modificarValoracionesGenerales(ValoracionesGenerales nuevoValor);
}