package br.gov.ba.inema.seiaauthserver.config.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Exceção para situações em que a senha informada seja inválida
 * @author tulio
 *
 */
public class InvalidPasswordException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String msg) {
		super(msg);
	}
}
