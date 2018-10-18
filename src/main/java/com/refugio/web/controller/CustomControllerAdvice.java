package com.refugio.web.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CustomControllerAdvice {

	@ModelAttribute("username")
	public String mensajeUsuario(Principal principal) {
		
		if (principal!=null) {
			return principal.getName();	
		 
		}
		return "";
	}
}
