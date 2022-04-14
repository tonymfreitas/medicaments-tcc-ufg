package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.classificationatc.level.ClassificacionAtc;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassificationAtcRepository extends CrudRepository<ClassificationAtcEntity, UUID> {

    @Override
    <S extends ClassificationAtcEntity> S save(S entity);

    @Override
    List<ClassificationAtcEntity> findAll();

    @Query(value = "select ca from classificationatc ca where ca.codeAtc = :codeatc")
    Optional<ClassificationAtcEntity> findByCode(@Param("codeatc") String codeatc);

    @Query(value = "select ca from classificationatc ca where ca.codeAtcParent = :codeatcparent and ca.codeAtc <> ca.codeAtcParent")
    List<ClassificationAtcEntity> findByCodeAtcParent(@Param("codeatcparent") String codeatcparent);

    @Modifying
    @Query("update classificationatc ca set ca.name = :name where ca.id = :uuid")
    @Transactional
    void update(@Param(value = "uuid") UUID uuid, @Param(value = "name") String name);

}