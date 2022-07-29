package br.com.ufg.tcc.medicamentos.availabilty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilibyService availabilibyService;

    @GetMapping
    public ResponseEntity<List<AvailabilibyEntity>> findAll() {
        return ResponseEntity.ok(availabilibyService.findAll());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity create(@RequestBody AvailabilibyEntity availabilibyEntity) {
        availabilibyService.save(availabilibyEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
