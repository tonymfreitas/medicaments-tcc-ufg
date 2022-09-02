package br.com.ufg.tcc.medicamentos.establishments;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstablishmentRepository extends CrudRepository<EstablishmentEntity, UUID> {

    @Override
    <S extends EstablishmentEntity> S save(S entity);

    @Override
    List<EstablishmentEntity> findAll();

}
