package br.gov.ba.inema.seiaauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.ba.inema.seiaauthserver.model.foreigntable.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query("SELECT u FROM #{#entityName} u"
				//Joins de Usuário
				+ " INNER JOIN FETCH u.pessoaFisica pf"
				+ " INNER JOIN FETCH pf.pessoa p"
				//Joins de Perfis
				+ " LEFT JOIN FETCH u.perfis perfis"
				+ " LEFT JOIN FETCH perfis.perfil perfil"
				+ " LEFT JOIN FETCH perfil.permissoes permissoes"
				+ " LEFT JOIN FETCH permissoes.sistemaModuloFuncionalidade smf"
				+ " LEFT JOIN FETCH smf.funcionalidade func"
				+ " LEFT JOIN FETCH func.acao acao"
				+ " LEFT JOIN FETCH func.secao secao"
				//Joins de Permissões Extras
				+ " LEFT JOIN FETCH u.permissoesExtras permissoes_extras"
				+ " LEFT JOIN FETCH permissoes_extras.sistemaModuloFuncionalidade smf_extra"
				+ " LEFT JOIN FETCH smf_extra.funcionalidade func_extra"
				+ " LEFT JOIN FETCH func_extra.acao acao_extra"
				+ " LEFT JOIN FETCH func_extra.secao secao_extra"
			+ " WHERE u.username = ?1"
				//Ands de Usuário
				//+ "	AND u.excluido = false"
				+ " AND p.excluido = false"
				//Ands de Perfis
				+ " AND (perfis.ativo IS NULL OR perfis.ativo = true)"
				+ " AND (perfis.dataExpiracao IS NULL OR CURRENT_DATE <= perfis.dataExpiracao)"
				+ " AND (perfil.ativo IS NULL OR perfil.ativo = true)"
				+ " AND (func.ativo IS NULL OR func.ativo = true)"
				+ " AND (func.excluido IS NULL OR func.excluido = false)"
				+ " AND (acao.ativo IS NULL OR acao.ativo = true)"
				+ " AND (acao.excluido IS NULL OR acao.excluido = false)"
				+ " AND (secao.ativo IS NULL OR secao.ativo = true)"
				+ " AND (secao.excluido IS NULL OR secao.excluido = false)"
				//Ands de Permissões Extras
				+ " AND (permissoes_extras.ativo IS NULL OR permissoes_extras.ativo = true)"
				+ " AND (permissoes_extras.dataExpiracao IS NULL OR CURRENT_DATE <= permissoes_extras.dataExpiracao)"
				+ " AND (func_extra.ativo IS NULL OR func_extra.ativo = true)"
				+ " AND (func_extra.excluido IS NULL OR func_extra.excluido = false)"
				+ " AND (acao_extra.ativo IS NULL OR acao_extra.ativo = true)"
				+ " AND (acao_extra.excluido IS NULL OR acao_extra.excluido = false)"
				+ " AND (secao_extra.ativo IS NULL OR secao_extra.ativo = true)"
				+ " AND (secao_extra.excluido IS NULL OR secao_extra.excluido = false)")
	public Usuario findByUsername(String username);
}
