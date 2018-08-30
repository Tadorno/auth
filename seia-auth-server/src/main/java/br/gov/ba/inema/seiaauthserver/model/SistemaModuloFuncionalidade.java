package br.gov.ba.inema.seiaauthserver.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="sistema_modulo_funcionalidade")
public class SistemaModuloFuncionalidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sq_sistema_modulo_funcionalidade_id", sequenceName = "sq_sistema_modulo_funcionalidade_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_sistema_modulo_funcionalidade_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_funcionalidade")
	private Funcionalidade funcionalidade;
	
	@ManyToOne
	@JoinColumn(name = "id_sistema_modulo")
	private SistemaModulo sistemaModulo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public SistemaModulo getSistemaModulo() {
		return sistemaModulo;
	}

	public void setSistemaModulo(SistemaModulo sistemaModulo) {
		this.sistemaModulo = sistemaModulo;
	}
	
}
