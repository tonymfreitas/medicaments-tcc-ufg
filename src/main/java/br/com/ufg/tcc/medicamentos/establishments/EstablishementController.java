package br.com.ufg.tcc.medicamentos.establishments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/establishements")
@Api(tags = "Establishement")
public class EstablishementController {

    @Autowired
    private EstablishmentService establishmentService;

    @PostMapping(path = "/file",
                 produces = "application/json")
    @ApiOperation(value = "Cadastra estabelecimentos de saúde por arquivo csv")
    public ResponseEntity<String> registerEstablishments(MultipartFile file) {
        establishmentService.save(file);
        return ResponseEntity.ok("File processed");
    }

}
