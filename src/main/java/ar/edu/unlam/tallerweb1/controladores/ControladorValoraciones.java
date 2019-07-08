package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.ValoracionAltura;
import ar.edu.unlam.tallerweb1.modelo.ValoracionEdad;
import ar.edu.unlam.tallerweb1.modelo.ValoracionPesoAltura;
import ar.edu.unlam.tallerweb1.servicios.ServicioValoracion;

@Controller
public class ControladorValoraciones {
	
	@Inject
	private ServicioValoracion servicioValoracion;
	
	@RequestMapping("/valoraciones")
	public ModelAndView mostrarValoraciones() {
		ModelMap modelo = new ModelMap();
		ValoracionEdad valorEdad = new ValoracionEdad();
		ValoracionAltura valorAltura = new ValoracionAltura();
		ValoracionPesoAltura valorPeso = new ValoracionPesoAltura();
		modelo.put("valoracionEdad", valorEdad);
		modelo.put("valoracionAltura", valorAltura);
		modelo.put("valoracionPesoAltura", valorPeso);
		modelo.put("listaValorEdad", servicioValoracion.verValoraciondeEdadTabla());
		modelo.put("listaValorAltura", servicioValoracion.verValoracionAlturaTabla());
		modelo.put("listaValorPA", servicioValoracion.listaValoracionPesoAltura());
		
		return new ModelAndView ("valoraciones", modelo);
	}

	// modifcar tabla edad
	@RequestMapping(path="/modificarTablaEdad", method=RequestMethod.POST)
	public ModelAndView modificarValoracionEdad(@ModelAttribute ("valoracionEdad") ValoracionEdad valoracionEdad) {
		ModelMap modelo = new ModelMap();
		ValoracionEdad valorEdad=servicioValoracion.trearValoracionEdadPorId(valoracionEdad.getId());
		modelo.put("valorEdad", valorEdad);
		return new ModelAndView("modificarTablaEdad",modelo);
	}
	
	@RequestMapping(path="/modificarEdadT", method=RequestMethod.POST)
	public ModelAndView modificadoEdad(@ModelAttribute ("valoracionEdad") ValoracionEdad valoracionEdad) {
		
		servicioValoracion.modificarTablaEdad(valoracionEdad);
		
		return new ModelAndView("redirect:/valoraciones");
	}
	// modificar tabla altura
	@RequestMapping(path="/modificarTablaAltura", method=RequestMethod.POST)
	public ModelAndView modificarValoracionAltura(@ModelAttribute ("valoracionAltura") ValoracionAltura valoracionAlt) {
		ModelMap modelo = new ModelMap();
		ValoracionAltura valorAltura=servicioValoracion.traerValoracionAlturaPorID(valoracionAlt.getId());
		modelo.put("valorAltura", valorAltura);
		ValoracionAltura valoracionAltura = new ValoracionAltura();
		modelo.put("valoracionAltura",valoracionAltura);
		return new ModelAndView("modificarTablaAltura",modelo);
	}
	@RequestMapping(path="/modificarAlturaT", method=RequestMethod.POST)
	public ModelAndView modificadoAltura(@ModelAttribute ("valoracionAltura") ValoracionAltura valoracionAltura) {
		
		servicioValoracion.modificarTablaAltura(valoracionAltura);;
		
		return new ModelAndView("redirect:/valoraciones");
	}
	// modificar tabla peso altura
	@RequestMapping(path="/modificarTablaPesoAltura", method=RequestMethod.POST)
	public ModelAndView modificarValoracionPesoAltura(@ModelAttribute ("valoracionPesoAltura") ValoracionPesoAltura valoracionPA) {
		ModelMap modelo = new ModelMap();
		ValoracionPesoAltura valorPesoAltura=servicioValoracion.traerValoracionPesoAlturaPorID(valoracionPA.getId());
		modelo.put("valorPesoAltura", valorPesoAltura);
		ValoracionPesoAltura valoracionPesoAltura = new ValoracionPesoAltura();
		modelo.put("valoracionPesoAltura",valoracionPesoAltura);
		return new ModelAndView("modificarTablaPesoAltura",modelo);
	}
	@RequestMapping(path="/modificarPesoAlturaT", method=RequestMethod.POST)
	public ModelAndView modificadoPesoAltura(@ModelAttribute ("valoracionPesoAltura") ValoracionPesoAltura valoracionPA) {
		
		servicioValoracion.modificarTablaPesoAltura(valoracionPA);;
		
		return new ModelAndView("redirect:/valoraciones");
	}

}