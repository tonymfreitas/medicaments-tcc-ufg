package br.com.ufg.tcc.medicamentos.medicament.pharmaceuticalform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PharmaceuticalFormService {

    @Autowired
    private PharmaceuticalFormRepository repository;

    public void save(PharmaceuticalFormEntity pharmaceuticalFormEntity) {
        pharmaceuticalFormEntity.setId(UUID.randomUUID());
        saveOrUpdate(pharmaceuticalFormEntity);
    }

    public void saveOrUpdate(PharmaceuticalFormEntity pharmaceuticalFormEntity) {
        repository.save(pharmaceuticalFormEntity);
    }

    public List<PharmaceuticalFormEntity> findAll() {
        return repository.findAll();
    }

}
