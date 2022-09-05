package br.com.ufg.tcc.medicamentos.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobSchedule {

    @Autowired
    private SendDataAwsJobService jobService;

    @Autowired
    private ProcessEstablishmentService processEstablishmentService;

    @Scheduled(fixedDelay = 3600000)
    public void sendDataToAws() {
        jobService.execute();
    }

    @Scheduled(fixedDelay = 60000)
    public void processEstablishment() {
        System.out.println("Processing establishment");
        processEstablishmentService.execute();
    }

    @Scheduled(fixedDelay = 60000)
    public void syncDataEstablishment() {
        System.out.println("Processing sync data establishments");
        processEstablishmentService.syncData();
        System.out.println("Finished sync data establishments");
    }

}
