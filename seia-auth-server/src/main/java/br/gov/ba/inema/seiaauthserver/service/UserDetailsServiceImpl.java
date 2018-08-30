package br.gov.ba.inema.seiaauthserver.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.ba.inema.seiaauthserver.model.PermissaoPerfil;
import br.gov.ba.inema.seiaauthserver.model.UsuarioPerfil;
import br.gov.ba.inema.seiaauthserver.model.UsuarioPermissaoExtra;
import br.gov.ba.inema.seiaauthserver.model.foreigntable.Usuario;
import br.gov.ba.inema.seiaauthserver.repository.UsuarioRepository;
import br.gov.ba.inema.seiaauthserver.service.model.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private static final String ADD = "ADICIONAR";

	private static final String REMOVE = "REMOVER";
    
	@Autowired
    private UsuarioRepository usuarioRepository;    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("Usuário %s não encontrado", username));
        }
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        		
        Set<String> listAuth = this.fillAuthority(user);
        
        listAuth.forEach(auth -> {
            authorities.add(new SimpleGrantedAuthority(auth));
        });
        
        
        return new CustomUserDetails(user, authorities);
    }
    
    /**
     * Retorna a lista de perfis/permissoes com as permissoes extras adicionadas/removidas
     * 
     * @param usuario
     * @return
     */
    
    private Set<String> fillAuthority(Usuario usuario) {
		Set<String> authorities = this.fillPerfil(usuario.getPerfis());
		
		Map<String, Set<String>> authoritiesMap = this.fillPermissaoExtra(usuario.getPermissoesExtras());
		
		authorities.removeAll(authoritiesMap.get(REMOVE));
		authorities.addAll(authoritiesMap.get(ADD));
		
		return authorities;
	}
    
    /**
     * Varre as lista de autorizações e retorna uma collection de perfis/permissoes no formado
     * esperado pelo Spring Security
     * 
     * @param perfis
     * @return
     */
    private Set<String> fillPerfil(Set<UsuarioPerfil> perfis) {
		Set<String> authorities = new HashSet<>();

		for (UsuarioPerfil usuarioPerfil : perfis) {
			authorities.add("ROLE_" + usuarioPerfil.getPerfil().getCodigo());
			for (PermissaoPerfil permissaoPerfil : usuarioPerfil.getPerfil().getPermissoes()) {
				authorities
						.add("OP_" + permissaoPerfil.getSistemaModuloFuncionalidade().getFuncionalidade().getCodigo());
			}
		}

		return authorities;
	}
    
    /**
     * Retorna um map contendo uma lista de permissoes a serem adicionadas e uma lista de permissoes a serem
     * removidas.
     * 
     * @param permissoes
     * @return
     */
    private Map<String, Set<String>> fillPermissaoExtra(Set<UsuarioPermissaoExtra> permissoes) {
		Map<String, Set<String>> authoritiesMap = new HashMap<>();
		authoritiesMap.put(ADD, new HashSet<>());
		authoritiesMap.put(REMOVE, new HashSet<>());

		for (UsuarioPermissaoExtra permissaoExtra : permissoes) {
			if (permissaoExtra.isPermissaoConcedida()) {
				authoritiesMap.get(ADD)
						.add("OP_" + permissaoExtra.getSistemaModuloFuncionalidade().getFuncionalidade().getCodigo());
			} else {
				authoritiesMap.get(REMOVE)
						.add("OP_" + permissaoExtra.getSistemaModuloFuncionalidade().getFuncionalidade().getCodigo());
			}
		}

		return authoritiesMap;
    }
    
}
