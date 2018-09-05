# Diagrama de sequência
![Visão Geral](diagramas/diagrama_de_sequencia.png?raw=true "Visão Geral")


    1 - Web app acessar oauth/token informando username, password e grant_type além do client_id e client_secret no Basic Authentication
    2 - O AuthorizationServer verifica no banco se o client_id e o client_secret existem
    3 - O AuthorizationServer recupera o usuário do banco juntamente com suas permissões
    4 - O AuthorizationServer cria o Token
    5 - O AuthorizationServer envia para o Web app o token gerado
    6 - O Web app deve armazenar o token nos cookies
    7 - O Web app acessa um recurso do ResourceServer enviando o token gerado no login
    8 - O ResourceServer valida o token no AuthorizationServer
    9 - O ResourceServer devolve ao Web app o recurso solicitado
    10 - Para realizar uma nova requisição, é necessário voltar ao passo 7, caso o token não tenha expirado
   
# seia-auth-server
Api que disponibiliza autenticação e autorização via Tokens.

Configurações importantes para o application.properties:

    1 - security.oauth2.resource.filter-order=3 (determina a ordem de prioridade do filtro)
    2 - security.signing-key=<chave para assinatura>
    3 - security.security-realm=<nome do Realm>
    

Endpoints importantes:

    1 - /oauth/token <--- Realizar o acesso informando Basic Authentication, password, grant_type e usuário
    2 - /oauth/check_token <--- Verifica se o token é válido (A Api que consome o serviço de autenticação deve validar o token a cada requisição)
     

# resource-security
Biblioteca que configura uma aplicação para se tornar um ResourceServer

Para instalar é necessário adicionar o jar ao projeto e realizar as seguintes configurações:

    1 - Criar uma classe e extender ResourceServerConfigAdapter e adicionar as anotações @EnableResourceServer e @Configuration

```javascript    
    import br.gov.ba.inema.resourcesecurity.ResourceServerConfigAdapter;

    @Configuration
    @EnableResourceServer
    public class ResourceServerConfig extends ResourceServerConfigAdapter{
    
    }
```

    2 - Criar uma classe e extender SecurityConfigAdapter e adicionar as anotações @Configuration, @EnableWebSecurity e @EnableGlobalMethodSecurity(prePostEnabled = true)

```javascript    
    import br.gov.ba.inema.resourcesecurity.SecurityConfigAdapter;

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig extends SecurityConfigAdapter{
    
    }
```

    3 - Para utilizar o AuthenticationUtil basta criar um bean retornando new AuthenticationUtil();
    
 ```javascript
 
    @Bean
	public AuthenticationUtil getAuthenticationUtil() {
		return new AuthenticationUtil();
	}
    
 ```

Configurações importantes para o application.properties:

    1 - seia.resource.client-id=<client_id>
    2 - seia.resource.client-secret=<client_secret>
    3 - seia.resource.check-token-url=<auth-server>/oauth/check_token
    4 - security.security-realm=<nome do Realm>
    5 - security.signing-key=<chave para assinatura>
    
 Protegendo endpoints:
 Para proteger os endpoints basta adicionar a anotação @PreAuthorize("hasAuthority('[Permissão]')")

# Instalação Wildfly
    1. Baixa e descompactar o Wildfly na pasta desejada
    2. Acessar a pasta bin do Wildfly e abrir o arquivo standalone.conf e cofigurar o JAVA_HOME="$JAVA_HOME" e JBOSS_HOME=”[caminho]/wildfly-10.1.0.Final”
    3. Na linha de comando executar ./standalone.sh para iniciar e testar o servidor
    
Se tudo ocorreu bem, será possível acessar a página de boas vindas do wildfly http://localhost:8080

# Adicionando usuário admin no Wildfly
    1. Acessar a pasta bin do Wildfly
    2. Abrir a linha de comando
    3. Digitar sh add-user.sh e pressionar Enter
    4. Seguir passos da instalação
    
    Obs. Para facilitar a utilização e migração do server, informar usuário e senha como "jboss"
    
Se tudo ocorreu bem, será possivel acessar a tela de administração do wildfly http://localhost:9990

# Instalando driver no Wildfly
    1. Baixe o drive na extensão .jar do postgres (http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.postgresql%22%20AND%20a%3A%22postgresql%22)
    2. Crie uma pasta /drivers dentro da pasta do Wildfly e adicione o driver
    3. Abra a linha de comando e acesse o bin
    4. Digitar o comando a seguir e pressionar Enter
        ./jboss-cli.sh --connect
    5. Digitar o comando a seguir e pressionar Enter
        module add --name=org.postgres --resources=/[caminho do wildfly]/drivers/postgresql-42.2.1.jre7.jar --dependencies=javax.api,javax.transaction.api
    6. Digitar o comando a seguir e pressionar Enter 
        /subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
    
Se tudo ocorreu bem, o console irá exibir a mensagem {"outcome" => "success"}
    
    
# Adicionando Datasource SeiaSegurancaAuth
    Com o Wildfly iniciado, realize os seguintes passos
    
    1. Acesse a tela de administração http://localhost:9990
    2. Acesse o menu Configuration e depois Subsystems > Datasources >Non -XA e acione o botão Add
    3. Selecione PostgreSQL Datasource e acione Next
    4. Informe o Name "SeiaSegurancaAuth" e JNDI Name "java:jboss/datasources/SeiaSegurancaAuth" e acione Next
    5. Clique Detected Driver e selecione o driver postgres, após isso acione Next
    6. Connection Url informe "jdbc:postgresql://[ip]:[porta]/seia_seguranca?currentSchema=seguranca", Username [username], Senha [password]
        e acione Next
    7. Acione Finish
    
    
Se tudo ocorreu bem, o Servidor irá exibir a mensagem de sucesso "Added Datasource SeiaSegurancaAuth"
    
# Adicionando Datasource SeiaSegurancaOauth
    Com o Wildfly iniciado, realize os seguintes passos
    
    1. Acesse a tela de administração http://localhost:9990
    2. Acesse o menu Configuration e depois Subsystems > Datasources >Non -XA e acione o botão Add
    3. Selecione PostgreSQL Datasource e acione Next
    4. Informe o Name "SeiaSegurancaOauth" e JNDI Name "java:jboss/datasources/SeiaSegurancaOauth" e acione Next
    5. Clique Detected Driver e selecione o driver postgres, após isso acione Next
    6. Connection Url informe "jdbc:postgresql://[ip]:[porta]/seia_seguranca?currentSchema=oauth", Username [username], Senha [password]
        e acione Next
    7. Acione Finish
    
    
Se tudo ocorreu bem, o Servidor irá exibir a mensagem de sucesso "Added Datasource SeiaSegurancaOauth"

# Utilizando a API para autenticar um usuário, obter o token e salvar na LocalStorage

```javascript

// Criando Objeto Base64
var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}

function login(username, password) {

    const esc = encodeURIComponent;
    const client_id = "<client_id>"
    const client_secret = "<secret>" 
    //encriptando o client_id e a secret
    const secret = Base64.encode(client_id + ":" + client_secret)
    //definindo o grant_type
    const grant_type = "password"
    //agrupando parametro em um array
    const params =  { 
                    username, 
                    password, 
                    grant_type 
                }

    const requestOptions = {
        method: 'POST', 
        headers: {
            'Authorization' : `Basic ${secret}`,
            'Content-Type':'application/x-www-form-urlencoded'
        },
        body: Object.keys(params).map(k => `${esc(k)}=${esc(params[k])}`).join('&')
    };

    //enviando requisição ao servidor
    return fetch(URL, requestOptions)
        .then(response => {
            if (!response.ok) { 
                return Promise.reject(response.statusText);
            }
            return response.json();
        })
        .then(user => {                        
            // se o login for realizado com sucesso o retorno será um token jwt no response            
            if (user && user.access_token) {             
                // armazenando os detalhes do usuario e o token na local storage para manter o usuário logado (mesmo em um refresh)                
                localStorage.setItem('user', JSON.stringify(user));
            }            
            return user;
        });
}

function logout() {
    // removendo usuário e token da local storage para realizar o logout
    localStorage.removeItem('user');
}


```

#### Caso o login seja efetuado com sucesso a resposta será um token conforme exemplo abaixo:

```json
{
  "access_token":"<hash_string>",
  "token_type":"bearer",
  "refresh_token":"<hash_string>",
  "expires_in": <int_number>,
  "scope":"<read write>",
  "jti":"<hash_string>"
}

```
