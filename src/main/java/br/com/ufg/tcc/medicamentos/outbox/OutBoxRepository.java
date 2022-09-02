package br.com.ufg.tcc.medicamentos.outbox;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

@Repository
public interface OutBoxRepository extends CrudRepository<OutboxEntity, UUID> {

    @Query(value = "select m from outbox m where m.event = :event and m.status = :status")
    List<OutboxEntity> listEventsByType(@Param("event") String event, @Param("status") String status);

    @Modifying
    @Query("update outbox o set o.status = :status where o.id = :id")
    @Transactional
    void updateStatus(@Param(value = "id") UUID id, @Param(value = "status") String status);

}
