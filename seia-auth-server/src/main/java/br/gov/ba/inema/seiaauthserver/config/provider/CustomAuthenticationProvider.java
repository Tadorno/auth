package br.gov.ba.inema.seiaauthserver.config.provider;

import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import br.gov.ba.inema.seiaauthserver.config.exception.InvalidPasswordException;
import br.gov.ba.inema.seiaauthserver.config.exception.NoAuthoritiyException;
import br.gov.ba.inema.seiaauthserver.config.exception.UsuarioInativoException;
import br.gov.ba.inema.seiaauthserver.service.model.CustomUserDetails;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("login null ou vazio");
		}
		
        // You can get the password here
        String password = authentication.getCredentials().toString();

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(name.toLowerCase().trim());
        
        this.validarPassword(password, user.getPassword());
        
        this.validarUsuarioAtivo(user);
        
        this.validarPermissoes(user.getAuthorities());
        
        UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        
        HashMap<String, Object> details = new HashMap<String, Object>();
        details.put("idUsuario", user.getId());
        details.put("area", user.getArea());
        details.put("matricula", user.getMatricula());
        details.put("nome", user.getNome());
        details.put("sgArea", user.getSiglaArea());

        upaToken.setDetails(details);
        
        return upaToken;
	}
	/**
	 * Valida se o usuário está ativo
	 * @param user
	 */
	private void validarUsuarioAtivo(UserDetails user) {
		if(!user.isEnabled()) {
			throw new UsuarioInativoException("Usuario inativo");
		}
	}
	
	/**
	 * Valida se a senha informada confere com a senha registrada para o usuário
	 * 
	 * @param entryPassword
	 * @param userPassword
	 */
	private void validarPassword(String entryPassword, String userPassword) {
		String md5Hex = DigestUtils.md5Hex(entryPassword);
		if(!md5Hex.equals(userPassword)) {
			throw new InvalidPasswordException("Senha inválida");
		}
	}
	
	/**
	 * Valida se o usuário possui alguma permissão
	 * 
	 * @param permissoes
	 */
	private void validarPermissoes(Collection<?extends GrantedAuthority> permissoes) {
		
		if(permissoes == null || permissoes.isEmpty()) {
			throw new NoAuthoritiyException("Usuário sem permissões");
		}
		
		int count = 0;
		//Verifica a quantidade de permissoes do tipo OP(Operação)
		for(GrantedAuthority permissao: permissoes) {
			if(permissao.toString().startsWith("OP_")) {
				count++;
			}
		}
		
		if(count == 0) {
			throw new NoAuthoritiyException("Usuário sem permissões");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
