package br.com.ufg.tcc.medicamentos.medicament;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicamentRepository extends CrudRepository<MedicamentEntity, UUID> {

    @Override
    <S extends MedicamentEntity> S save(S entity);

    @Override
    List<MedicamentEntity> findAll();

    @Query(value = "select m from medicaments m where m.codeAtc like :codeatcgroup%")
    List<MedicamentEntity> getMedicamentByGroup(@Param("codeatcgroup") String codeatcgroup);

}