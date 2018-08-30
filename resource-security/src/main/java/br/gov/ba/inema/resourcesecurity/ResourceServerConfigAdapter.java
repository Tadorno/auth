package br.gov.ba.inema.resourcesecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import br.gov.ba.inema.resourcesecurity.util.CustomAuthenticationEntryPoint;

/**
 * 
 * @author tadorno
 *
 */

public abstract class ResourceServerConfigAdapter extends ResourceServerConfigurerAdapter {
	
	@Value("${security.signing-key}")
	private String signingKey;

	@Autowired
	private RemoteTokenServices tokenServices;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenServices).authenticationEntryPoint(new CustomAuthenticationEntryPoint()).tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/swagger-ui.html**").permitAll();
		http.requestMatchers().and().authorizeRequests().antMatchers("/api/**").authenticated();
	}
	
	@Bean
	@Primary
	public RemoteTokenServices tokenService(@Value("${seia.resource.client-id}") String clientId,
			@Value("${seia.resource.client-secret}") String clientSecret, @Value("${seia.resource.check-token-url}") String url) {
		
		RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setCheckTokenEndpointUrl(url);
		tokenService.setClientId(clientId);
		tokenService.setClientSecret(clientSecret);
		return tokenService;
	}
	
	@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
 
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }
		
}