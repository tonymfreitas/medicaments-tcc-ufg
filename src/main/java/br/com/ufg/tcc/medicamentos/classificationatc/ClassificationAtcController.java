package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.classificationatc.level.ClassificacionAtc;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/atc")
@Api(tags = "Classification ATC")
public class ClassificationAtcController {

    @Autowired
    private ClassificationAtcService service;

    @GetMapping(value = "/test")
    @ApiIgnore
    public ResponseEntity.BodyBuilder getTest() {
        return ResponseEntity.ok();
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Todas as classificações ATC")
    public List<ClassificacionAtc> findAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{uuid}",
                produces = "application/json")
    @ApiOperation(value = "Classificação ATC por id")
    public ClassificacionAtc findById(@PathVariable UUID uuid) {
        return service.findById(uuid);
    }

    @GetMapping(path = "/code/{codeAtc}",
                produces = "application/json")
    @ApiOperation(value = "Classificação ATC por código")
    public ClassificacionAtc findByCodeAtc(@PathVariable String codeAtc) {
        return service.findByCode(codeAtc);
    }

    @PostMapping(consumes = "application/json",
                 produces = "application/json")
    @ApiOperation(value = "Cadastra uma nova classificação ATC")
    public ResponseEntity<ResponseApi> createClassificationAtc(@RequestBody ClassificacionAtc classificacionAtc) {
        service.save(classificacionAtc);
        return ResponseEntity.ok(ResponseApi.builder()
                                            .message("Classificação ATC cadastrada com sucesso.")
                                            .status(HttpStatus.OK)
                                            .build());
    }

    @PutMapping(consumes = "application/json",
                produces = "application/json")
    @ApiOperation(value = "Atualiza o cadastro de uma classificação ATC")
    public ResponseEntity<ResponseApi> updateClassificationAtc(@RequestBody ClassificacionAtc classificacionAtc) {
        service.update(classificacionAtc);
        return ResponseEntity.ok(ResponseApi.builder()
                                            .message("Classificação ATC alterada com sucesso.")
                                            .status(HttpStatus.OK)
                                            .build());
    }

    @ApiIgnore
    @PostMapping(path = "/save",
                 produces = "application/json")
    @ApiOperation(value = "Cadastra classificação atc e medicamentos por arquivo .xlsx")
    public String registerClassificationAtc(MultipartFile file) {
        service.save(file);
        return "OK";
    }

}
