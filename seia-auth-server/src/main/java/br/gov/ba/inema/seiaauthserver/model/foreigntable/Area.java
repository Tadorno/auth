package br.gov.ba.inema.seiaauthserver.model.foreigntable;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="area")
public class Area implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ide_area")
	private Integer id;
	
	@Column(name="nom_area")
	private String descricao;
	
	@Column(name="ind_excluido")
	private boolean excluido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_exclusao")
	private Date dataExcluido;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Date getDataExcluido() {
		return dataExcluido;
	}

	public void setDataExcluido(Date dataExcluido) {
		this.dataExcluido = dataExcluido;
	}
	
}
