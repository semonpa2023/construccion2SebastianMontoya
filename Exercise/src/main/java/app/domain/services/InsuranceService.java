package app.domain.services;

import app.domain.entities.Insurance;
import app.domain.repositories.InsuranceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para la gestión de seguros médicos.
 * Proporciona métodos para crear nuevos seguros y listar todos los registros de seguros existentes.
 */
@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    /**
     * Guarda un registro de seguro médico en la base de datos.
     * @param insurance Objeto Insurance a guardar.
     * @return El seguro médico guardado.
     */
    public Insurance save(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    /**
     * Obtiene todos los registros de seguros médicos existentes.
     * @return Lista de seguros médicos.
     */
    public List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }
}
