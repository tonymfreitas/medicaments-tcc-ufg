package br.com.ufg.tcc.medicamentos.medicament;

import br.com.ufg.tcc.medicamentos.classificationatc.AwsClassificationAtcService;
import br.com.ufg.tcc.medicamentos.classificationatc.level.ClassificacionAtc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicamentService {

    @Autowired
    private MedicamentRepository repository;

    @Autowired
    private AwsClassificationAtcService awsClassificationAtcService;

    public List<MedicamentEntity> getAll() {
        return repository.findAll();
    }

    public List<MedicamentEntity> getMedicamentByGroup(String codeatcgroup) {
        return repository.getMedicamentByGroup(codeatcgroup);
    }

    public void saveOrUpdate(MedicamentEntity medicament) {
        repository.save(medicament);
    }

    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    public void sendDataToAws() {
        awsClassificationAtcService.sendMedicaments(getAll());
    }
}
