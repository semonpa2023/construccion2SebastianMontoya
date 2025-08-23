/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.domain.repository;

/**
 *
 * @author Diego
 */
import java.math.BigDecimal;
import java.util.Optional;

public interface DiagnosticCatalogRepository {
    boolean existsByName(String diagnosticName);
    Optional<BigDecimal> priceOf(String diagnosticName);
}
