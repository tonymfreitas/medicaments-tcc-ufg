package br.com.ufg.tcc.medicamentos.availabilty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvailabilibyService {

    @Autowired
    private AvailabilibyRepository repository;

    public void save(final Availabiliby entity) {
        entity.setId(UUID.randomUUID());
        repository.save(entity);
    }

    public List<Availabiliby> listAll() {
        return repository.getAll();
    }

    public List<Availabiliby> findByState(final String state) {
        return repository.findByState(state);
    }

    public List<Availabiliby> listByState(final String state) {
        return repository.findByState(state);
    }
}
