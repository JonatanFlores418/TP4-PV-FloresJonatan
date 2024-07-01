package ar.edu.unju.fi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.collections.ListadoDocente;
import ar.edu.unju.fi.model.Docente;
@Controller
public class DocenteController {
	
	@Autowired
	Docente nuevoDocente = new Docente();
	
	@GetMapping("/formularioDeDocentes")
    public ModelAndView getFormularioDocentes() {
		//vista de formDocente
		ModelAndView modelView = new ModelAndView("formularioDeDocentes");
		modelView.addObject("nuevoDocente", nuevoDocente);
        return modelView;
    }
	
	@PostMapping("/guardarDocente")
	public ModelAndView saveDocente(@ModelAttribute("nuevoDocente") Docente docenteAGuardar) {
		//guardar
		ListadoDocente.agregarDocente(docenteAGuardar);
		//mostrar el listado
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocente", ListadoDocente.listarDocentes());	
		return modelView;	
	}
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView deleteDocenteDelListado(@PathVariable(name="legajo") String legajo) {
		//borrar
		ListadoDocente.eliminarDocente(legajo);
		//mostrar el nuevo listado
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocente", ListadoDocente.listarDocentes());	
		return modelView;		
	}
	
	@GetMapping("/modificarDocente/{legajo}")
	public ModelAndView editDocente(@PathVariable(name="legajo") String legajo) {
		//seleccionar el docente para modificar
		Docente docenteAModificar = ListadoDocente.buscarDocentePorLegajo(legajo);
		
		//mostrar el formulario de modificacion
		ModelAndView modelView = new ModelAndView("formularioDocentes");
		modelView.addObject("nuevoDocente", docenteAModificar);	
		modelView.addObject("flag", true);
		return modelView;		
		}
	
	@PostMapping("/modificarDocente")
	public ModelAndView updateDocente(@ModelAttribute("nuevoDocente") Docente docenteModificado) {
					
		//modificar el docente
		ListadoDocente.modificarDocente(docenteModificado);
		
		//mostrar el listado
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocente", ListadoDocente.listarDocentes());	
		return modelView;		
	}
	
	@GetMapping("/docentes")
	public ModelAndView showDocentes() {
		//mostrar el listado
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocente", ListadoDocente.listarDocentes());	
		return modelView;		
	}
}