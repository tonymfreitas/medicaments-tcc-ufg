package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.classificationatc.level.ClassificacionAtc;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/atc")
@Api(tags = "Classification ATC")
public class ClassificationAtcController {

    @Autowired
    private ClassificationAtcService service;

    @GetMapping(value = "/test")
    public String getAtcTeste() {
        return "TESTE ENDPOINT ATC";
    }

    @GetMapping(produces = "application/json")
    public List<ClassificacionAtc> getAll() {
        return service.getAll();
    }

    @ApiIgnore
    @PostMapping(path = "/save", produces = "application/json")
    public String registerClassificationAtc() {
        service.save();
        return "OK";
    }

}
