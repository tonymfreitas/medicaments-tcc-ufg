package br.com.ufg.tcc.medicamentos;

import br.com.ufg.tcc.medicamentos.config.AwsCredentialsConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final static Logger logger = LoggerFactory.getLogger(MedicamentosApplication.class);


    public static void main(String[] args) {
        logger.info("Starting application");
        SpringApplication.run(MedicamentosApplication.class, args);
    }

}
