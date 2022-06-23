package com.example.microcloud;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;


@ConfigurationProperties(prefix = "microcloud.oauth-server")
public class AuthServerProps {

    private final Token token = new Token();
    private final Jwk jwk = new Jwk();

    public Token getToken() {
        return token;
    }

    public Jwk getJwk() {
        return jwk;
    }

    public static class Token {

        private int accessValiditySeconds = 3600 * 12; // default 12 hours.
        private int refreshValiditySeconds = 3600 * 24 * 30; // default 30 days.

        public int getAccessValiditySeconds() {
            return accessValiditySeconds;
        }

        public void setAccessValiditySeconds(int accessValiditySeconds) {
            this.accessValiditySeconds = accessValiditySeconds;
        }

        public int getRefreshValiditySeconds() {
            return refreshValiditySeconds;
        }

        public void setRefreshValiditySeconds(int refreshValiditySeconds) {
            this.refreshValiditySeconds = refreshValiditySeconds;
        }
    }

    public static class Jwk {
        private ClassPathResource keystoreLocation;
        private String keystoreAlias;
        private String keystorePassword;
        private String keyId;

        public ClassPathResource getKeystoreLocation() {
            return keystoreLocation;
        }

        public void setKeystoreLocation(ClassPathResource keystoreLocation) {
            this.keystoreLocation = keystoreLocation;
        }

        public String getKeystoreAlias() {
            return keystoreAlias;
        }

        public void setKeystoreAlias(String keystoreAlias) {
            this.keystoreAlias = keystoreAlias;
        }

        public String getKeystorePassword() {
            return keystorePassword;
        }

        public void setKeystorePassword(String keystorePassword) {
            this.keystorePassword = keystorePassword;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }
    }
}
