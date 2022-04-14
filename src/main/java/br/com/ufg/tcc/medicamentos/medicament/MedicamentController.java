package br.com.ufg.tcc.medicamentos.medicament;

import br.com.ufg.tcc.medicamentos.classificationatc.level.ClassificacionAtc;
import br.com.ufg.tcc.medicamentos.common.ResponseApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicament")
@Api(tags = "Medicaments")
public class MedicamentController {

    @Autowired
    private MedicamentService service;

    @GetMapping(produces = "application/json")
    public List<MedicamentEntity> findAll() {
        return service.getAll();
    }

    @GetMapping(path="/group/{codeAtcGroup}" ,produces = "application/json")
    public List<MedicamentEntity> findByCodeGroup(@PathVariable String codeAtcGroup) {
        return service.getMedicamentByGroup(codeAtcGroup);
    }

    @PostMapping(consumes =  "application/json", produces = "application/json")
    public ResponseEntity<ResponseApi> createMedicament(@RequestBody MedicamentEntity medicament) {
        service.saveOrUpdate(medicament);
        return ResponseEntity.ok(ResponseApi.builder().message("Medicamento cadastrado com sucesso.").status(HttpStatus.OK).build());
    }

    @PutMapping(consumes =  "application/json", produces = "application/json")
    public ResponseEntity<ResponseApi> updateMedicament(@RequestBody MedicamentEntity medicament) {
        service.saveOrUpdate(medicament);
        return ResponseEntity.ok(ResponseApi.builder().message("Medicamento atualizado com sucesso.").status(HttpStatus.OK).build());
    }

    @DeleteMapping(path = "/{uuid}", produces = "application/json")
    public ResponseEntity<ResponseApi> deleteMedicament(@PathVariable UUID uuid) {
        service.delete(uuid);
        return ResponseEntity.ok(ResponseApi.builder().message("Medicamento deletado com sucesso.").status(HttpStatus.OK).build());
    }


}
