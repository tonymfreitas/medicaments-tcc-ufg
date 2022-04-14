package br.com.ufg.tcc.medicamentos.job;

import br.com.ufg.tcc.medicamentos.classificationatc.ClassificationAtcService;
import br.com.ufg.tcc.medicamentos.medicament.MedicamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendDataAwsJobService {

    final Logger LOGGER = LoggerFactory.getLogger(SendDataAwsJobService.class);

    @Autowired
    private ClassificationAtcService classificationAtcService;

    @Autowired
    private MedicamentService medicamentService;
    public void execute() {
        LOGGER.info("Sendind classifications and medicaments to AWS bucket S3");
        classificationAtcService.sendDataToAws();
        medicamentService.sendDataToAws();
    }

}
