package br.gov.ba.inema.seiaauthserver.model.foreigntable;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.ba.inema.seiaauthserver.model.UsuarioPerfil;
import br.gov.ba.inema.seiaauthserver.model.UsuarioPermissaoExtra;

@Entity(name = "usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(
		    name="pessoa_pessoa_fisica",
		    pkColumnValue="ide_pessoa_fisica"
		)
		@Column(name="ide_pessoa_fisica")
	private Integer id;
	
	@OneToOne
    @JoinColumn(name = "ide_pessoa_fisica")
	private PessoaFisica pessoaFisica;
	
	@Column(name="dsc_login")
	private String username;
	
	@Column(name="dsc_senha")
	private String senha;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_criacao")
	private Date dataCriacao;
	
	@Column(name="ind_excluido")
	private boolean excluido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_exclusao")
	private Date dataExclusao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<UsuarioPerfil> perfis;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<UsuarioPermissaoExtra> permissoesExtras;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Set<UsuarioPerfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<UsuarioPerfil> perfis) {
		this.perfis = perfis;
	}

	public Set<UsuarioPermissaoExtra> getPermissoesExtras() {
		return permissoesExtras;
	}

	public void setPermissoesExtras(Set<UsuarioPermissaoExtra> permissoesExtras) {
		this.permissoesExtras = permissoesExtras;
	}

}
