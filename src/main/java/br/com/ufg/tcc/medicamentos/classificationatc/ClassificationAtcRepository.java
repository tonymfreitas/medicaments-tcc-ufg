package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.medicament.MedicamentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassificationAtcRepository extends CrudRepository<ClassificationAtcEntity, UUID> {

    @Override
    <S extends ClassificationAtcEntity> S save(S entity);

    @Override
    List<ClassificationAtcEntity> findAll();

}