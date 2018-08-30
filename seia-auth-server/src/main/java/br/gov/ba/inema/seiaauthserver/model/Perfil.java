package br.gov.ba.inema.seiaauthserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="perfil")
public class Perfil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sq_perfil_id", sequenceName = "sq_perfil_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_perfil_id")
	private Integer id;
	
	private String nome;
	
	private String codigo;
	
	private boolean ativo;
		
	private String descricao;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "perfil")
	private List<PermissaoPerfil> permissoes = new ArrayList<>();

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

	public List<PermissaoPerfil> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoPerfil> permissoes) {
		this.permissoes = permissoes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
