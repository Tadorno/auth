package br.gov.ba.inema.resourcesecurity.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(this.getBody(exception));

	}

	private String getBody(AuthenticationException exception) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		Throwable cause = exception.getCause();
		if (exception instanceof InsufficientAuthenticationException && cause == null) {
			MessageResponse standardError = new MessageResponse(HttpServletResponse.SC_UNAUTHORIZED, "MSG25", "Acesso Negado!");
			standardError.addDetalhe("Autenticação necessária para acessar este recurso.");

			return mapper.writeValueAsString(standardError);
		} else if (cause instanceof InvalidTokenException) {
			MessageResponse standardError = new MessageResponse(HttpServletResponse.SC_UNAUTHORIZED, "MSG42", "Sessão expirada ou usuário não logado! Favor logar novamente.");
			standardError.addDetalhe("Token Inválido.");

			return mapper.writeValueAsString(standardError);
		} else {
			MessageResponse standardError = new MessageResponse(HttpServletResponse.SC_UNAUTHORIZED,"MSG4", "Erro interno no sistema!");

			return mapper.writeValueAsString(standardError);
		}

	}
}
