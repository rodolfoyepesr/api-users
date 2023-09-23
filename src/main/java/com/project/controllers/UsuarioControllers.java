package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.services.interfaces.UsuarioService;
import com.project.vo.ErrorResponseVO;
import com.project.vo.UsuarioRequestVO;
import com.project.vo.UsuarioResponseVO;

@RestController
@RequestMapping("users")
public class UsuarioControllers {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("/add")
	public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRequestVO request){
		ErrorResponseVO error = new ErrorResponseVO();
		
		if (service.emailIsPresent(request.getEmail())) {
			error.setMensaje("El correo ya registrado");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		
		if (service.emailInvalid(request.getEmail())) {
			error.setMensaje("El correo ingresado es inválido");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		
		if (!service.validatePwd(request.getPassword())) {
			error.setMensaje("La clave no cumple con las politicas minimas de seguridad");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		
		UsuarioResponseVO response = service.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(service.findAll());
	}
}
