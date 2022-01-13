package br.com.ufg.tcc.medicamentos.medicament;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicament")
@Api(tags = "Medicaments")
public class MedicamentController {

    @Autowired
    private MedicamentService service;

    @GetMapping(produces = "application/json")
    public List<MedicamentEntity> getAll() {
        return service.getAll();
    }

    @GetMapping(path="/group/{codeAtcGroup}" ,produces = "application/json")
    public List<MedicamentEntity> getAll(@PathVariable String codeAtcGroup) {
        return service.getMedicamentByGroup(codeAtcGroup);
    }


}
