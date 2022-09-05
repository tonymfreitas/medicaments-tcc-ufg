package br.com.ufg.tcc.medicamentos.establishments;

import br.com.ufg.tcc.medicamentos.MedicamentosApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class EstablishmentController {

    final static Logger logger = LoggerFactory.getLogger(EstablishmentController.class);


    @Autowired
    private EstablishmentService establishmentService;

    @PostMapping(path="sync-data", produces = "application/json")
    @ApiOperation(value = "")
    public ResponseEntity<String> registerEstablishments() {
        establishmentService.saveRequestSyncData();
        logger.info("Processing request is running.");
        return ResponseEntity.ok("Processing request is running.");
    }

}
