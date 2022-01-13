package br.com.ufg.tcc.medicamentos.medicament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentService {

    @Autowired
    private MedicamentRepository repository;

    public List<MedicamentEntity> getAll() {
        return repository.findAll();
    }

    public List<MedicamentEntity> getMedicamentByGroup(String codeatcgroup) {
        return repository.getMedicamentByGroup(codeatcgroup);
    }
}
