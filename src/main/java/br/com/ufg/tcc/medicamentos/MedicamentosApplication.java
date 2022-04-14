package br.com.ufg.tcc.medicamentos;

import br.com.ufg.tcc.medicamentos.config.AwsCredentialsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(AwsCredentialsConfig.class)
@EnableScheduling
public class MedicamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicamentosApplication.class, args);
	}

}
