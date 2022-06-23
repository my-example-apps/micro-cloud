package com.example.microcloud.endpoint;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JwkSetEndpoint {

    private final JWKSet jwkSet;

    public JwkSetEndpoint(JWKSet jwkSet) {
        this.jwkSet = jwkSet;
    }

    @GetMapping("/jwk.json")
    @ResponseBody
    public Map<String, Object> getKey() {
        return jwkSet.toJSONObject();
    }
}