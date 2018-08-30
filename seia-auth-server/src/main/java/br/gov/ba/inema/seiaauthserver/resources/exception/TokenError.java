package br.gov.ba.inema.seiaauthserver.resources.exception;

import java.io.Serializable;

/**
 * 
 * @author tulio
 *
 */
public class TokenError implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String error;
	
	private String error_description;
	
	public TokenError(String error, String error_description) {
		super();
		this.error = error;
		this.error_description = error_description;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

}
