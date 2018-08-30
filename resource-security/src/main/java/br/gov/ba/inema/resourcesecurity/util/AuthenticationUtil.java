package br.gov.ba.inema.resourcesecurity.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Prover métodos que extrai dados do token informado na autenticação
 * @author tulio
 *
 */
public class AuthenticationUtil {

	@Autowired
	private TokenStore tokenStore;
	
	public OAuth2Authentication getAuthentication() {
		return (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
	}
	
	/**
	 * Retorna o usuario logado
	 * @return
	 */
	public String getUsuarioLogado() {
		Authentication auth = this.getAuthentication();
		
		if(auth != null) {
			return auth.getName();
		}
		return null;
	}
	
	/**
	 * Retorna a área do usuário logado
	 * @return
	 */
	public Integer getIdArea() {
		Integer value = null;
		
		Map<String, Object> map = this.getAllInfo();
		
		if(map.get("area")!= null) {
			value = Integer.parseInt(map.get("area").toString());
		}
		
		return value;
	}
	
	/**
	 * Retorna o id do usuário logado
	 * @return
	 */
	public Integer getIdUsuario() {
		Integer value = null;
		
		Map<String, Object> map = this.getAllInfo();
		
		if(map.get("idUsuario")!= null) {
			value = Integer.parseInt(map.get("idUsuario").toString());
		}
		
		return value;
	}
	
	/**
	 * Retorna todas as informações do token
	 * @return
	 */
	public Map<String, Object> getAllInfo() {
		OAuth2Authentication auth = this.getAuthentication();
		
	    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
	    OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
	    return accessToken.getAdditionalInformation();
	}
}
