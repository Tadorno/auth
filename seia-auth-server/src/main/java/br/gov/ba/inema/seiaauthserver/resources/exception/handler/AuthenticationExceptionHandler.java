package br.gov.ba.inema.seiaauthserver.resources.exception.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.gov.ba.inema.seiaauthserver.config.exception.InvalidPasswordException;
import br.gov.ba.inema.seiaauthserver.config.exception.NoAuthoritiyException;
import br.gov.ba.inema.seiaauthserver.config.exception.UsuarioInativoException;
import br.gov.ba.inema.seiaauthserver.resources.exception.StandardError;
import br.gov.ba.inema.seiaauthserver.resources.exception.TokenError;

@ControllerAdvice
public class AuthenticationExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<TokenError> handleAll(InvalidTokenException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
		TokenError err = new TokenError("invalid_token", e.getMessage());
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    } 
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardError> handleAll(UsernameNotFoundException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), "MSG20", "Usuário inexistente!", System.currentTimeMillis(), e.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    } 
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<StandardError> handleAll(InvalidPasswordException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), "MSG21", "Senha inválida!", System.currentTimeMillis(), e.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoAuthoritiyException.class)
    public ResponseEntity<StandardError> handleAll(NoAuthoritiyException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), "MSG44", "Usuário sem permissão para acessar o sistema! Favor entrar em contato com o suporte.", System.currentTimeMillis(), e.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsuarioInativoException.class)
    public ResponseEntity<StandardError> handleAll(UsuarioInativoException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), "MSG43", "Usuário não ativo no sistema! Favor entrar em contato com o suporte.", System.currentTimeMillis(), e.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleAll(IllegalArgumentException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), "MSG24", "Dados inválidos.", System.currentTimeMillis(), e.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
}