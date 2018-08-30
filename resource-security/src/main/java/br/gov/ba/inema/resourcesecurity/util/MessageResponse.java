package br.gov.ba.inema.resourcesecurity.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author tulio
 *
 */
public class MessageResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer status;
	
	private String msg;
	
	private String cod;
	
	private Long timeStamp;
	
	private List<String> detalhe = new ArrayList<>();
	
	private Integer HTTP_STATUS_200 = 200;
	
	public MessageResponse(String cod, String msg) {
		super();
		this.status = HTTP_STATUS_200;
		this.cod = cod;
		this.msg = msg;
		this.timeStamp = System.currentTimeMillis();
	}
	
	public MessageResponse(Integer status, String cod, String msg) {
		super();
		this.status = status;
		this.cod = cod;
		this.msg = msg;
		this.timeStamp = System.currentTimeMillis();
	}
	
	public MessageResponse(Integer status, String cod, String msg, String detalhe) {
		super();
		this.status = status;
		this.cod = cod;
		this.msg = msg;
		this.timeStamp = System.currentTimeMillis();
		this.detalhe.add(detalhe);
	}
		
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<String> getDetalhe() {
		return detalhe;
	}

	public void addDetalhe(String detalhe) {
		this.detalhe.add(detalhe);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public void setDetalhe(List<String> detalhe) {
		this.detalhe = detalhe;
	}

}
