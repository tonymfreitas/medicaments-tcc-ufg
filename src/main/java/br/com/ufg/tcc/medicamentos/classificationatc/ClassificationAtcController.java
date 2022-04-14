package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.classificationatc.level.ClassificacionAtc;
import br.com.ufg.tcc.medicamentos.common.ResponseApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/atc")
@Api(tags = "Classification ATC")
public class ClassificationAtcController {

    @Autowired
    private ClassificationAtcService service;

    @GetMapping(value = "/test")
    public ResponseEntity.BodyBuilder getTest() {
        return ResponseEntity.ok();
    }

    @GetMapping(produces = "application/json")
    public List<ClassificacionAtc> findAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{uuid}", produces = "application/json")
    public ClassificacionAtc findById(@PathVariable UUID uuid) {
        return service.findById(uuid);
    }

    @GetMapping(path = "/code/{codeAtc}", produces = "application/json")
    public ClassificacionAtc findByCodeAtc(@PathVariable String codeAtc) {
        return service.findByCode(codeAtc);
    }

    @PostMapping(consumes =  "application/json", produces = "application/json")
    public ResponseEntity<ResponseApi> createClassificationAtc(@RequestBody ClassificacionAtc classificacionAtc) {
        service.save(classificacionAtc);
        return ResponseEntity.ok(ResponseApi.builder().message("Classificação ATC cadastrada com sucesso.").status(HttpStatus.OK).build());
    }

    @PutMapping(consumes =  "application/json", produces = "application/json")
    public ResponseEntity<ResponseApi> updateClassificationAtc(@RequestBody ClassificacionAtc classificacionAtc) {
        service.update(classificacionAtc);
        return ResponseEntity.ok(ResponseApi.builder().message("Classificação ATC alterada com sucesso.").status(HttpStatus.OK).build());
    }

    @DeleteMapping(path = "/{uuid}", produces = "application/json")
    public ResponseEntity<ResponseApi> deleteMedicament(@PathVariable UUID uuid) {
        service.delete(uuid);
        return ResponseEntity.ok(ResponseApi.builder().message("Classificação ATC deletado com sucesso.").status(HttpStatus.OK).build());
    }

    @ApiIgnore
    @PostMapping(path = "/save", produces = "application/json")
    public String registerClassificationAtc() {
        service.save();
        return "OK";
    }

}
