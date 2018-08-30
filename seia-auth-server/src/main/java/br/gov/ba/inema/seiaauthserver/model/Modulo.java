package br.gov.ba.inema.seiaauthserver.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="modulo")
public class Modulo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sq_modulo_id", sequenceName = "sq_modulo_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_modulo_id")
	private Integer id;
	
	private String nome;
	
	private String codigo;
	
	private boolean ativo;
	
	private boolean excluido;
	
	@ManyToOne
	@JoinColumn(name = "id_sistema_proprietario")
	private Sistema sistemaProprietario;
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Sistema getSistemaProprietario() {
		return sistemaProprietario;
	}

	public void setSistemaProprietario(Sistema sistemaProprietario) {
		this.sistemaProprietario = sistemaProprietario;
	}
	
}
