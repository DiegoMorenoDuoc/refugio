package com.refugio.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.refugio.web.DAO.AnimalDAO;
import com.refugio.web.DAO.FamiliaDAO;
import com.refugio.web.DAO.RazaDAO;
import com.refugio.web.entity.Animal;
import com.refugio.web.entity.Raza;

@Controller
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private AnimalDAO aDAO;
	
	@Autowired
	private RazaDAO rDAO;
	
	@Autowired
	private FamiliaDAO fDAO;
	
	
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("animales", aDAO.crud().findAll());
		
		return "listar.html";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		
		model.addAttribute("razas", rDAO.crud().findAll());
		model.addAttribute("familias", fDAO.crud().findAll());
		
		return "agregar.html";
	}
	
	@PostMapping("/almacenar")
	public String almacenar(Model model,
			RedirectAttributes ra,
			@RequestParam("txtNombre") String nombre,
			@RequestParam("txtPeso") float peso,
			@RequestParam("txtFechaIngreso")
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date fechaIngreso,
			@RequestParam("cboRaza") int razaId) {
			
		Raza raza = new Raza();
		raza.setId(razaId);
		
		Animal animal = new Animal();
		animal.setNombre(nombre);
		animal.setPeso(peso);
		animal.setFechaIngreso(fechaIngreso);
		animal.setRaza(raza);
		
		//guardamos el animal y comprobamos que se haya
		//insertado correctamente
		Animal animalAgregado = aDAO.crud().save(animal);
		String mensaje = "Error al agregar";
		if(animalAgregado!= null) {
			mensaje = "Guardado correctamente";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:crear";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(Model model,
			RedirectAttributes ra,
			@RequestParam("id") int id) {
		
		String mensaje = "";
		
		try {
			//eliminamos al animal
			aDAO.crud().deleteById(id);
			mensaje = "Eliminado correctamente";
		} catch(Exception ex) {
			mensaje = "No se ha podido eliminar";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listar";
	}
	
	
	@GetMapping("/modificar")
	public String modificar(Model model,
			RedirectAttributes ra,
			@RequestParam("id") int id) {
		
		//buscamos al animal
		Animal a = null;
		
		try {
			
			a = aDAO.crud().findById(id).get();
			
		} catch (Exception e) {
			
			//si el animal no existe en la BBDD
			//lo redirigimos de vuelta con un mensaje de error
			ra.addFlashAttribute("mensaje", "El animal no existe");
			return "redirect:listar";
		}
		
		//si encuentra el animal lo enviamos a la vista
		model.addAttribute("a", a);
		
		//mandamos las razas y familias para llenar los combobox
		model.addAttribute("razas", rDAO.crud().findAll());
		model.addAttribute("familias", fDAO.crud().findAll());
		
		return "modificar.html";
	}
	
	
	@PostMapping("/actualizar")
	public String actualizar(Model model,
			RedirectAttributes ra,
			@RequestParam("txtId") int id,
			@RequestParam("txtNombre") String nombre,
			@RequestParam("txtPeso") float peso,
			@RequestParam("txtFechaIngreso")
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date fechaIngreso,
			@RequestParam("cboRaza") int razaId) {
			
		Raza raza = new Raza();
		raza.setId(razaId);
		
		Animal animal = new Animal();
		animal.setId(id);
		animal.setNombre(nombre);
		animal.setPeso(peso);
		animal.setFechaIngreso(fechaIngreso);
		animal.setRaza(raza);
		
		//guardamos el animal y comprobamos que se haya
		//insertado correctamente
		Animal animalAgregado = aDAO.crud().save(animal);
		String mensaje = "Error al modificar";
		if(animalAgregado!= null) {
			mensaje = "Modificado correctamente";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listar";
	}
	
	
	
	
	
}
