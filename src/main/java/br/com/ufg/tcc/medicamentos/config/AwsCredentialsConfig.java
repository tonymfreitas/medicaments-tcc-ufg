package br.com.ufg.tcc.medicamentos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.credentials")
public class AwsCredentialsConfig {

    private String accessKey;
    private String secretKey;

    private String bucketName;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKe) {
        this.accessKey = accessKe;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
