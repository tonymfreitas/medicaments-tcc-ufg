package br.com.ufg.tcc.medicamentos.availabilty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilibyService availabilibyService;

    @GetMapping
    public ResponseEntity<List<Availabiliby>> listAll() {
        return ResponseEntity.ok(availabilibyService.listAll());
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Availabiliby>> listByState(@PathVariable String state) {
        return ResponseEntity.ok(availabilibyService.listByState(state));
    }

    @PostMapping(produces = "application/json",
                 consumes = "application/json")
    public ResponseEntity create(@RequestBody Availabiliby availabilibyEntity) {
        availabilibyService.save(availabilibyEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .build();
    }

}
