package br.gov.ba.inema.seiaauthserver.service.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.gov.ba.inema.seiaauthserver.model.foreigntable.Usuario;

public class CustomUserDetails extends User{

	private static final long serialVersionUID = 1L;

	public CustomUserDetails(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getUsername(), usuario.getSenha(), !usuario.isExcluido(), true, true, true, authorities);
				
		if(usuario.getPessoaFisica().getFuncionario() != null) {
			this.matricula = usuario.getPessoaFisica().getFuncionario().getMatricula();
			this.nome = usuario.getPessoaFisica().getNome();
			this.id = usuario.getId();
			
			if(usuario.getPessoaFisica().getFuncionario().getPk().getArea()!=null) {
				this.area = usuario.getPessoaFisica().getFuncionario().getPk().getArea().getId();
				this.siglaArea = usuario.getPessoaFisica().getFuncionario().getPk().getArea().getDescricao().split("-")[0].trim().toUpperCase();
				this.areaCompleta = usuario.getPessoaFisica().getFuncionario().getPk().getArea().getDescricao();
			}
		}
	}

	private Object area;
	
	private Integer id;
	
	private String matricula;
	
	private String nome;
	
	private String siglaArea;
	
	private String areaCompleta;

	public Object getArea() {
		return area;
	}

	public void setArea(Object area) {
		this.area = area;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSiglaArea() {
		return siglaArea;
	}

	public void setSiglaArea(String siglaArea) {
		this.siglaArea = siglaArea;
	}

	public String getAreaCompleta() {
		return areaCompleta;
	}

	public void setAreaCompleta(String areaCompleta) {
		this.areaCompleta = areaCompleta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
