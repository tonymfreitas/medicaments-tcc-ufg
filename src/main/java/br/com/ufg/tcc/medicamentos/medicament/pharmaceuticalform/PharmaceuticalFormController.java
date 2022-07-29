package br.com.ufg.tcc.medicamentos.medicament.pharmaceuticalform;

import br.com.ufg.tcc.medicamentos.common.ResponseApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmaceutical-form")
@Api(tags = "Pharmaceutical Form")
public class PharmaceuticalFormController {

    @Autowired
    private PharmaceuticalFormService service;

    @GetMapping
    public List<PharmaceuticalFormEntity> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseApi> create(@RequestBody PharmaceuticalFormEntity pharmaceuticalFormEntity) {
        service.save(pharmaceuticalFormEntity);
        return ResponseEntity.ok(ResponseApi.builder().message("Forma farmaceutica cadastrado com sucesso.").status(HttpStatus.OK).build());
    }


    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseApi> update(@RequestBody PharmaceuticalFormEntity pharmaceuticalFormEntity) {
        service.saveOrUpdate(pharmaceuticalFormEntity);
        return ResponseEntity.ok(ResponseApi.builder().message("Forma farmaceutica atualizada com sucesso.").status(HttpStatus.OK).build());
    }


}
