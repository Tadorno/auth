package br.gov.ba.inema.seiaauthserver.config.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Exceção para situações em que o usuário não possui permissões no sistema
 * @author tulio
 *
 */
public class NoAuthoritiyException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoAuthoritiyException(String msg) {
		super(msg);
	}
}
