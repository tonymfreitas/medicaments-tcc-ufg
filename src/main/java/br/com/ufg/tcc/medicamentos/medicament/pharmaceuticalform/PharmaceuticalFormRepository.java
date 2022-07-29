package br.com.ufg.tcc.medicamentos.medicament.pharmaceuticalform;

import br.com.ufg.tcc.medicamentos.medicament.MedicamentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PharmaceuticalFormRepository extends CrudRepository<PharmaceuticalFormEntity, UUID> {


    @Override
    <S extends PharmaceuticalFormEntity> S save(S entity);

    @Override
    List<PharmaceuticalFormEntity> findAll();

    @Query(value = "select m from pharmaceuticalform m where m.description = :description")
    PharmaceuticalFormEntity findByDescription();

    @Query(value = "select m from pharmaceuticalform m where m.code = :code")
    PharmaceuticalFormEntity findByCode(@Param("code") String code);

}
