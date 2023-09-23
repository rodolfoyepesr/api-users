package com.project.services.interfaces;

import com.project.models.entity.Usuario;
import com.project.vo.UsuarioRequestVO;
import com.project.vo.UsuarioResponseVO;

public interface UsuarioService {
	
	public UsuarioResponseVO save(UsuarioRequestVO usuarioVO);
	
	public boolean emailInvalid(String email);
	
	public boolean emailIsPresent(String email);
	
	public boolean validatePwd(String password);
	
	public Iterable<Usuario> findAll();

}
