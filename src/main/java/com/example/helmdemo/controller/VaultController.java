package com.example.helmdemo.controller;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaultController {

     public final SecretClient secretClient;

    public VaultController(SecretClient secretClient) {
        this.secretClient = secretClient;
    }
    @GetMapping("/getSecretValue/{key}")
    public String getSecretValue(@PathVariable("key") String key){
         KeyVaultSecret secret=secretClient.getSecret(key);
         return secret.getValue();
    }
}
