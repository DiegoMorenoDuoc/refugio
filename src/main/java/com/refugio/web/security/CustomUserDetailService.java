package com.refugio.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.refugio.web.DAO.UsuarioDAO;
import com.refugio.web.entity.Usuario;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Usuario usuario = usuarioDAO.buscarPorNombreUsuario(username);
		
		UserBuilder user = null;
		
		if (usuario!=null) {
			
			user = org.springframework.security.core.userdetails.User.withUsername(username);
			user.password(new BCryptPasswordEncoder().encode(usuario.getPassword()));
			user.roles("USER");
			
			
		}else{
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return user.build();
		
		
	
	}

}
