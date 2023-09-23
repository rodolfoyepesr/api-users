package com.project.services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.models.entity.Telefono;
import com.project.models.entity.Usuario;
import com.project.models.repository.UsuarioRepository;
import com.project.services.interfaces.UsuarioService;
import com.project.vo.Constants;
import com.project.vo.UsuarioRequestVO;
import com.project.vo.UsuarioResponseVO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public UsuarioResponseVO save(UsuarioRequestVO usuarioVO) {
		Usuario usuario = new Usuario();
		List<Telefono> phones = new ArrayList<>();
		UsuarioResponseVO response = new UsuarioResponseVO();
		
		if (!usuarioVO.getPhones().isEmpty()) {
			usuarioVO.getPhones().forEach(phone -> {
				Telefono telefono = new Telefono();
				telefono.setNumber(phone.getNumber());
				telefono.setCitycode(phone.getCitycode());
				telefono.setCountrycode(phone.getCountrycode());	
				telefono.setUsuario(usuario);
				phones.add(telefono);
			});
		}
		
		usuario.setUuid(java.util.UUID.randomUUID().toString());
		usuario.setName(usuarioVO.getName());
		usuario.setEmail(usuarioVO.getEmail());
		usuario.setPassword(usuarioVO.getPassword());
		usuario.setActive(Boolean.TRUE);
		usuario.setToken(getJWTToken(usuarioVO.getEmail()));
		usuario.setPhones(phones);
		Usuario persist = usuarioRepository.save(usuario);
		
		response.setId(persist.getUuid());
		response.setCreated(persist.getCreate());
		response.setModified(persist.getModified());
		response.setLastLogin(persist.getLastLogin());
		response.setToken(persist.getToken());
		response.setIsactive(persist.isActive() == Boolean.TRUE ? "ACTIVE" : "INACTIVE");
		
		return response;
	}
	
	@Override
	public boolean emailInvalid(String email) {
		Pattern pattern = Pattern
	                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		
		if (mather.find() == false) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean emailIsPresent(String email) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
		
		if (usuarioOpt.isPresent()) {
			return true;
		}
		return false;
	}
	
	@Override
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public boolean validatePwd(String password) {	
		Pattern pattern = Pattern
                .compile(getRegex());
		Matcher mather = pattern.matcher(password);
		
		if (mather.find() == true) {
            return true;
		}
	
		return false;
	}
	
	public String getRegex() {
		String digito = Constants.DIGITO==Boolean.TRUE ? "(?=.*[0-9])" : "";
		String minuscula = Constants.MINUSCULA==Boolean.TRUE ? "(?=.*[a-z])" : "";
		String mayuscula = Constants.MAYUSCULA==Boolean.TRUE ? "(?=.*[A-Z])" : "";
		String caracterEsp = Constants.CARACTER_ESPECIAL==Boolean.TRUE ? "(?=.*[@#$%^&+=])" : "";
		int minimo = Constants.MINIMO;
		int maximo = Constants.MAXIMO;
		
		String regex = "^" + digito
                + minuscula
                + mayuscula
                + caracterEsp
                + "(?=\\S+$).{"+minimo+","+maximo+"}$";
		
		return regex;
		
	}
	
	private String getJWTToken(String email) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("jwt")
				.setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
