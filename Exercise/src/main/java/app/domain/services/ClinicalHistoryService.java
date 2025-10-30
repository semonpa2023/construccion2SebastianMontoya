package app.domain.services;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Servicio NoSQL simulado: mapa por paciente (cedula) -> mapa fechaAtencion -> record
 * Esto evita añadir Mongo ahora. En producción se sustituye por MongoRepository.
 */
import org.springframework.stereotype.Service;

@Service
public class ClinicalHistoryService {

    // clave=cedulaPaciente -> (clave=fechaISO -> registro)
    private final Map<String, TreeMap<LocalDateTime, Map<String, Object>>> store = new HashMap<>();

    /**
     * Agrega una nota clínica (consulta) a la historia del paciente.
     * La subclave es la fecha/hora de atención.
     */
    public void addConsultation(String patientDocument, LocalDateTime at, Map<String, Object> data) {
        store.computeIfAbsent(patientDocument, k -> new TreeMap<>())
             .put(at, new LinkedHashMap<>(data));
    }

    public Map<LocalDateTime, Map<String, Object>> getHistory(String patientDocument) {
        return store.getOrDefault(patientDocument, new TreeMap<>());
    }

    // Añade/actualiza diagnóstico final para una fecha de atención: se busca por timestamp exacto.
    public boolean addFinalDiagnosis(String patientDocument, LocalDateTime at, String finalDiagnosis) {
        TreeMap<LocalDateTime, Map<String, Object>> map = store.get(patientDocument);
        if (map == null || !map.containsKey(at)) return false;
        map.get(at).put("finalDiagnosis", finalDiagnosis);
        map.get(at).put("finalizedAt", LocalDateTime.now().toString());
        return true;
    }
}
