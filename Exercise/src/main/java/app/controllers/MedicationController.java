package app.controllers;

import app.domain.entities.Medication;
import app.domain.services.MedicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    // GET: Ver todos los medicamentos
    @GetMapping
    public ResponseEntity<List<Medication>> getAll() {
        return ResponseEntity.ok(medicationService.findAll());
    }

    // POST: Crear medicamento
    @PostMapping
    public ResponseEntity<Medication> create(@RequestBody Medication medication) {
        return ResponseEntity.ok(medicationService.save(medication));
    }

    // PUT: Editar medicamento
    @PutMapping("/{id}")
    public ResponseEntity<Medication> update(
            @PathVariable Long id,
            @RequestBody Medication medication
    ) {
        return ResponseEntity.ok(medicationService.save(medication));
    }

    // DELETE: Eliminar medicamento
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        medicationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
