package br.com.ufg.tcc.medicamentos.availabilty;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AvailabilibyRepository extends CrudRepository<AvailabilibyEntity, UUID> {

    @Override
    <S extends AvailabilibyEntity> S save(S entity);

    @Override
    List<AvailabilibyEntity> findAll();

}
