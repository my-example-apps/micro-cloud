package com.example.microcloud.config;

import com.example.microcloud.AuthServerProps;
import com.example.props.AuthServerProps;
import com.example.service.impl.AgentUserTokenEnhancer;
import com.example.service.impl.CustomerUserTokenEnhancer;
import com.example.service.impl.DefaultTokenEnhancer;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;

@Configuration
public class JwtTokenConfig {

    private final AuthServerProps authServerProps;

    public JwtTokenConfig(AuthServerProps authServerProps) {
        this.authServerProps = authServerProps;
    }

    @Bean
    @Order(99)
    public TokenEnhancer customerTokenEnhancer() {
        return new CustomerUserTokenEnhancer();
    }

    @Bean
    @Order(100)
    public TokenEnhancer agentTokenEnhancer(JdbcTemplate jdbcTemplate) {
        return new AgentUserTokenEnhancer(jdbcTemplate);
    }

    @Bean
    @Order(101)
    public TokenEnhancer sessionIdTokenEnhancer() {
        return new DefaultTokenEnhancer();
    }

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter accessTokenConverter) {
        return new JwtTokenStore(accessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(KeyPair keyPair) {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        char[] passwordChars = authServerProps.getJwk().getKeystorePassword().toCharArray();
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(authServerProps.getJwk().getKeystoreLocation(), passwordChars);
        return ksFactory.getKeyPair(authServerProps.getJwk().getKeystoreAlias(), passwordChars);
    }

    @Bean
    public JWKSet jwkSet(KeyPair keyPair) {
        RSAKey.Builder keyBuilder = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(authServerProps.getJwk().getKeyId());
        return new JWKSet(Collections.singletonList(keyBuilder.build()));
    }
}