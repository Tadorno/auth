package br.gov.ba.inema.seiaauthserver.model.foreigntable;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="funcionario")
public class Funcionario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FuncionarioPK pk = new FuncionarioPK();
	
	private String matricula;
	
	@ManyToOne
	@JoinColumn(name = "ide_pessoa_fisica", insertable=false, updatable=false)
	@JsonIgnore
	private PessoaFisica pessoaFisica;

	public FuncionarioPK getPk() {
		return pk;
	}

	public void setPk(FuncionarioPK pk) {
		this.pk = pk;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	
}
