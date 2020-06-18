package com.examen.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examen.Service.UsuarioService;
import com.examen.interfaceService.IUsuarioService;
import com.examen.modelo.Usuario;


@Controller
@RequestMapping
public class UsuarioController {
    @Autowired
	private IUsuarioService service; 
    
    private UsuarioService us;
	
    
    @GetMapping("/listar")
	public String listar (Model model) {
		List<Usuario> usuario=service.listar();
		model.addAttribute("personas",usuario);
		return "index";
	}
    
    @GetMapping("/")
   	public String inicio (Model model) {
    	model.addAttribute("usuario", new Usuario());
   		return "Login";
   	}
    @GetMapping("/registro")
   	public String registrar (Model model) {
    	model.addAttribute("usuario", new Usuario());
   		return "Registro";
   	}
    @PostMapping("/login")
    public String home(Model model,HttpServletRequest request) {
    	String u=request.getParameter("u");
    	String c=request.getParameter("p");
    	Usuario usuer=us.find(u, c);
    	if(usuer == null) {return "Error";}
    	return "home";
    }
    
    @PostMapping("/save")
    public String save(@Validated Usuario u,Model model ) {
    	service.save(u);
    	return "redirect:/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
    	Optional<Usuario> usuario=service.listarId(id);
    	model.addAttribute("usuario",usuario);
    	return "Form";
    }
    @GetMapping("/eliminar/{id}")
    public String delete(Model model, @PathVariable int id) {
    	service.delete(id);
    	return "redirect:/listar";
    }
    
    
}