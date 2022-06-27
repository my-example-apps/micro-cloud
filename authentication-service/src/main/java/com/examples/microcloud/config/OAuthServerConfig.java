package com.examples.microcloud.config;

import com.examples.microcloud.config.AuthServerProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class OAuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AccessTokenConverter accessTokenConverter;
    private final List<TokenEnhancer> tokenEnhancerList;
    private final TokenStore tokenStore;
    private final AuthenticationManager authenticationManager;
    private final AuthServerProps authServerProps;
    private final DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public OAuthServerConfig(AccessTokenConverter accessTokenConverter,
                             List<TokenEnhancer> tokenEnhancerList,
                             TokenStore tokenStore,
                             AuthenticationManager authenticationManager,
                             AuthServerProps authServerProps,
                             DataSource dataSource) {
        this.accessTokenConverter = accessTokenConverter;
        this.tokenEnhancerList = tokenEnhancerList;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
        this.authServerProps = authServerProps;
        this.dataSource=dataSource;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenServices(tokenServices())
                .tokenEnhancer(tokenEnhancerChain())
                .accessTokenConverter(accessTokenConverter);
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(authServerProps.getToken().getAccessValiditySeconds());
        tokenServices.setTokenEnhancer(tokenEnhancerChain());
        //tokenServices.setClientDetailsService(clientDetailsService());
        return tokenServices;
    }

    @Bean
    TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancerList);
        return tokenEnhancerChain;
    }

}
