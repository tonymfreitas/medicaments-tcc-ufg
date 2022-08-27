package br.com.ufg.tcc.medicamentos;

import br.com.ufg.tcc.medicamentos.config.AwsCredentialsConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties(AwsCredentialsConfig.class)
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"br.com.ufg.tcc.medicamentos"},
                       exclude = {
                               LiquibaseAutoConfiguration.class
                       })
public class MedicamentosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicamentosApplication.class, args);
    }

}
