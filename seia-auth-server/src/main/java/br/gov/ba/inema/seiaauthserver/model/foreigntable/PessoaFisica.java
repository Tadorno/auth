package br.gov.ba.inema.seiaauthserver.model.foreigntable;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="pessoa_fisica")
public class PessoaFisica implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(
	    name="pessoa",
	    pkColumnValue="ide_pessoa"
	)
	@Column(name="ide_pessoa_fisica")
	private Integer id;
	
	@OneToOne
    @JoinColumn(name = "ide_pessoa_fisica")
	private Pessoa pessoa;
	
	@Column(name="nom_pessoa")
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dtc_nascimento")
	private Date dataNascimento;
	
	@Column(name = "des_naturalidade")
	private String naturalidade;
	
	@Column(name = "nom_pai")
	private String nomePai;
	
	@Column(name = "nom_mae")
	private String nomeMae;
	
	@Column(name = "num_cpf")
	private String cpf;
	
	@OneToOne(mappedBy="pessoaFisica")
	private Funcionario funcionario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
