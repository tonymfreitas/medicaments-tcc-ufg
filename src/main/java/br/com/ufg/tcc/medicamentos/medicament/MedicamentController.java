package br.com.ufg.tcc.medicamentos.medicament;

import br.com.ufg.tcc.medicamentos.common.ResponseApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/medicament")
@Api(tags = "Medicaments")
public class MedicamentController {

    @Autowired
    private MedicamentService service;

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Todos os medicamentos")
    public List<MedicamentEntity> findAll() {
        return service.getAll();
    }

    @GetMapping(path = "/group/{codeAtcGroup}",
                produces = "application/json")
    @ApiOperation(value = "Medicamento por grupo ATC")
    public List<MedicamentEntity> findByCodeGroup(@PathVariable String codeAtcGroup) {
        return service.getMedicamentByGroup(codeAtcGroup);
    }

    @PostMapping(consumes = "application/json",
                 produces = "application/json")
    @ApiOperation(value = "Cadastra um novo medicamento")
    public ResponseEntity<ResponseApi> createMedicament(@RequestBody MedicamentEntity medicament) {
        service.save(medicament);
        return ResponseEntity.ok(ResponseApi.builder()
                                            .message("Medicamento cadastrado com sucesso.")
                                            .status(HttpStatus.OK)
                                            .build());
    }

    @PutMapping(consumes = "application/json",
                produces = "application/json")
    @ApiOperation(value = "Atualiza o cadastro de um medicamento")
    public ResponseEntity<ResponseApi> updateMedicament(@RequestBody MedicamentEntity medicament) {
        service.saveOrUpdate(medicament);
        return ResponseEntity.ok(ResponseApi.builder()
                                            .message("Medicamento atualizado com sucesso.")
                                            .status(HttpStatus.OK)
                                            .build());
    }

    @DeleteMapping(path = "/{uuid}",
                   produces = "application/json")
    @ApiIgnore
    public ResponseEntity<ResponseApi> deleteMedicament(@PathVariable UUID uuid) {
        service.delete(uuid);
        return ResponseEntity.ok(ResponseApi.builder()
                                            .message("Medicamento deletado com sucesso.")
                                            .status(HttpStatus.OK)
                                            .build());
    }


}
