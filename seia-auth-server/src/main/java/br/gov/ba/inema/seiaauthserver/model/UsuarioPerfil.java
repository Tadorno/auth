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

@Entity(name="usuario_perfil")
public class UsuarioPerfil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sq_usuario_perfil_id", sequenceName = "sq_usuario_perfil_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario_perfil_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;
	
	private String justificativa;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_cadastro")
	private Date dataCadastro;
	
	private boolean perfilTemporario;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_expiracao_perfil_temporario")
	private Date dataExpiracao;
	
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

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

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public boolean isPerfilTemporario() {
		return perfilTemporario;
	}

	public void setPerfilTemporario(boolean perfilTemporario) {
		this.perfilTemporario = perfilTemporario;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
