package br.com.ufg.tcc.medicamentos.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {

    @Override
    <S extends AddressEntity> S save(S entity);

    @Override
    List<AddressEntity> findAll();

}
