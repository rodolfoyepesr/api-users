package com.project.services.implemantations;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import com.project.models.entity.Usuario;
import com.project.models.repository.UsuarioRepository;
import com.project.services.implementations.UsuarioServiceImpl;
import com.project.vo.TelefonoVO;
import com.project.vo.UsuarioRequestVO;

public class UsuarioServiceImplTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private UsuarioServiceImpl service;
	
	@ExtendWith(MockitoExtension.class)
	@Test
	public void testSave() {
		Usuario usuario = new Usuario();
		Mockito.when(usuarioRepository.save(any())).thenReturn(usuario);

		assertNotNull(service.save(getUsuarioRequest()));
	}
	
	private UsuarioRequestVO getUsuarioRequest() {
		UsuarioRequestVO request = new UsuarioRequestVO();
		request.setName("Rodolfo Yepes");
		request.setEmail("rodolfo.yepes@developer.org");
		request.setPassword("Rodolfo@10");
		
		List<TelefonoVO> telefonos = new ArrayList<>();
		TelefonoVO telefonoVO = new TelefonoVO();
		telefonoVO.setNumber(304484L);
		telefonoVO.setCitycode(1L);
		telefonoVO.setCountrycode(57L);
		telefonos.add(telefonoVO);
		
		request.setPhones(telefonos);
		
		return request;
	}
	
}
