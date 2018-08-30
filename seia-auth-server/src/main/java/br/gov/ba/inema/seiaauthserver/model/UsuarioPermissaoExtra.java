package br.gov.ba.inema.seiaauthserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.ba.inema.seiaauthserver.model.foreigntable.Usuario;

@Entity(name="usuario_permissao_extra")
public class UsuarioPermissaoExtra implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sq_usuario_permissao_extra_id", sequenceName = "sq_usuario_permissao_extra_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario_permissao_extra_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_sistema_modulo_funcionalidade")
	private SistemaModuloFuncionalidade sistemaModuloFuncionalidade;
	
	private String justificativa;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_cadastro")
	private Date dataCadastro;
	
	private boolean permissaoTemporaria;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_expiracao_permissao_temporaria")
	private Date dataExpiracao;
	
	private boolean ativo;
	
	private boolean permissaoConcedida;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SistemaModuloFuncionalidade getSistemaModuloFuncionalidade() {
		return sistemaModuloFuncionalidade;
	}

	public void setSistemaModuloFuncionalidade(SistemaModuloFuncionalidade sistemaModuloFuncionalidade) {
		this.sistemaModuloFuncionalidade = sistemaModuloFuncionalidade;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean isPermissaoTemporaria() {
		return permissaoTemporaria;
	}

	public void setPermissaoTemporaria(boolean permissaoTemporaria) {
		this.permissaoTemporaria = permissaoTemporaria;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isPermissaoConcedida() {
		return permissaoConcedida;
	}

	public void setPermissaoConcedida(boolean permissaoConcedida) {
		this.permissaoConcedida = permissaoConcedida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
