package br.gov.ba.inema.seiaauthserver.model.foreigntable;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="pessoa")
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ide_pessoa")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_criacao")
	private Date dataCriacao;
	
	@Column(name="des_email")
	private String email;
	
	@Column(name="ind_excluido")
	private boolean excluido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_exclusao")
	private Date dataExclusao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
