package br.gov.ba.inema.seiaauthserver.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="permissao_perfil")
public class PermissaoPerfil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sq_permissao_perfil_id", sequenceName = "sq_permissao_perfil_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_permissao_perfil_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name = "id_sistema_modulo_funcionalidade")
	private SistemaModuloFuncionalidade sistemaModuloFuncionalidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public SistemaModuloFuncionalidade getSistemaModuloFuncionalidade() {
		return sistemaModuloFuncionalidade;
	}

	public void setSistemaModuloFuncionalidade(SistemaModuloFuncionalidade sistemaModuloFuncionalidade) {
		this.sistemaModuloFuncionalidade = sistemaModuloFuncionalidade;
	}
	
	
}
